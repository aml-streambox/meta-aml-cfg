#!/usr/bin/env python3

import functools
import os
import socket
from http.server import SimpleHTTPRequestHandler, ThreadingHTTPServer
from urllib.parse import urlsplit


class SbsWebUiHandler(SimpleHTTPRequestHandler):
    def handle_one_request(self):
        try:
            first_bytes = self.connection.recv(2, socket.MSG_PEEK)
        except OSError:
            first_bytes = b""
        if first_bytes.startswith(b"\x16\x03"):
            self.send_https_on_http_response()
            return
        return super().handle_one_request()

    def parse_request(self):
        if self.raw_requestline.startswith(b"\x16\x03"):
            self.send_https_on_http_response()
            return False
        return super().parse_request()

    def send_https_on_http_response(self):
        body = (
            "This is the SBS WebUI HTTP port, but it received HTTPS/TLS traffic.\n"
            "Open the WebUI with http://<device-address>:8080/ instead of https://.\n"
        ).encode("utf-8")
        self.requestline = "TLS handshake on HTTP port"
        self.request_version = "HTTP/1.0"
        self.command = None
        self.path = ""
        self.close_connection = True
        self.send_response(400, "HTTPS traffic received on HTTP port")
        self.send_header("Content-Type", "text/plain; charset=utf-8")
        self.send_header("Content-Length", str(len(body)))
        self.send_header("Connection", "close")
        self.end_headers()
        self.wfile.write(body)

    def request_path(self):
        return urlsplit(getattr(self, "path", "")).path

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
