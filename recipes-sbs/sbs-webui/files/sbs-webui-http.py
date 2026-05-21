#!/usr/bin/env python3

import functools
import os
from http.server import SimpleHTTPRequestHandler, ThreadingHTTPServer
from urllib.parse import urlsplit


class SbsWebUiHandler(SimpleHTTPRequestHandler):
    def request_path(self):
        return urlsplit(self.path).path

    def send_head(self):
        path = self.request_path()
        if path == "/" or path.endswith(".html"):
            # Yocto images use reproducible mtimes, so stale browser caches can
            # otherwise keep an old index.html that references missing assets.
            if "If-Modified-Since" in self.headers:
                del self.headers["If-Modified-Since"]
            if "If-None-Match" in self.headers:
                del self.headers["If-None-Match"]
        return super().send_head()

    def end_headers(self):
        path = self.request_path()
        if path == "/" or path.endswith(".html"):
            self.send_header("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0")
            self.send_header("Pragma", "no-cache")
            self.send_header("Expires", "0")
        elif "/assets/" in path:
            self.send_header("Cache-Control", "public, max-age=31536000, immutable")
        super().end_headers()


def main():
    host = os.environ.get("SBS_WEBUI_HOST", "0.0.0.0")
    port = int(os.environ.get("SBS_WEBUI_PORT", "8080"))
    directory = os.environ.get("SBS_WEBUI_ROOT", "/var/www/sbs-webui")
    handler = functools.partial(SbsWebUiHandler, directory=directory)
    with ThreadingHTTPServer((host, port), handler) as httpd:
        httpd.serve_forever()


if __name__ == "__main__":
    main()
