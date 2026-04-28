#include <stddef.h>
#include <stdint.h>
#include <string.h>

#define LIBYUV_ERR (-1)

enum FilterMode {
    kFilterNone = 0,
    kFilterLinear = 1,
    kFilterBilinear = 2,
    kFilterBox = 3,
};

enum RotationMode {
    kRotate0 = 0,
    kRotate90 = 90,
    kRotate180 = 180,
    kRotate270 = 270,
};

static void zero_plane(uint8_t *dst, int stride, int width, int height) {
    int y;

    if (!dst || stride <= 0 || width <= 0 || height <= 0) {
        return;
    }

    for (y = 0; y < height; y++) {
        memset(dst + (size_t)y * (size_t)stride, 0, (size_t)width);
    }
}

int YUY2ToI420(const uint8_t* src_yuy2, int src_stride_yuy2,
               uint8_t* dst_y, int dst_stride_y,
               uint8_t* dst_u, int dst_stride_u,
               uint8_t* dst_v, int dst_stride_v,
               int width, int height) {
    (void)src_yuy2; (void)src_stride_yuy2; (void)dst_y; (void)dst_stride_y;
    (void)dst_u; (void)dst_stride_u; (void)dst_v; (void)dst_stride_v;
    (void)width; (void)height;
    return LIBYUV_ERR;
}

int YUY2ToNV12(const uint8_t* src_yuy2, int src_stride_yuy2,
               uint8_t* dst_y, int dst_stride_y,
               uint8_t* dst_uv, int dst_stride_uv,
               int width, int height) {
    (void)src_yuy2; (void)src_stride_yuy2; (void)dst_y; (void)dst_stride_y;
    (void)dst_uv; (void)dst_stride_uv; (void)width; (void)height;
    return LIBYUV_ERR;
}

int UYVYToI420(const uint8_t* src_uyvy, int src_stride_uyvy,
               uint8_t* dst_y, int dst_stride_y,
               uint8_t* dst_u, int dst_stride_u,
               uint8_t* dst_v, int dst_stride_v,
               int width, int height) {
    (void)src_uyvy; (void)src_stride_uyvy; (void)dst_y; (void)dst_stride_y;
    (void)dst_u; (void)dst_stride_u; (void)dst_v; (void)dst_stride_v;
    (void)width; (void)height;
    return LIBYUV_ERR;
}

int UYVYToNV12(const uint8_t* src_uyvy, int src_stride_uyvy,
               uint8_t* dst_y, int dst_stride_y,
               uint8_t* dst_uv, int dst_stride_uv,
               int width, int height) {
    (void)src_uyvy; (void)src_stride_uyvy; (void)dst_y; (void)dst_stride_y;
    (void)dst_uv; (void)dst_stride_uv; (void)width; (void)height;
    return LIBYUV_ERR;
}

int I422ToI420(const uint8_t* src_y, int src_stride_y,
               const uint8_t* src_u, int src_stride_u,
               const uint8_t* src_v, int src_stride_v,
               uint8_t* dst_y, int dst_stride_y,
               uint8_t* dst_u, int dst_stride_u,
               uint8_t* dst_v, int dst_stride_v,
               int width, int height) {
    (void)src_y; (void)src_stride_y; (void)src_u; (void)src_stride_u;
    (void)src_v; (void)src_stride_v; (void)dst_y; (void)dst_stride_y;
    (void)dst_u; (void)dst_stride_u; (void)dst_v; (void)dst_stride_v;
    (void)width; (void)height;
    return LIBYUV_ERR;
}

int I444ToI420(const uint8_t* src_y, int src_stride_y,
               const uint8_t* src_u, int src_stride_u,
               const uint8_t* src_v, int src_stride_v,
               uint8_t* dst_y, int dst_stride_y,
               uint8_t* dst_u, int dst_stride_u,
               uint8_t* dst_v, int dst_stride_v,
               int width, int height) {
    (void)src_y; (void)src_stride_y; (void)src_u; (void)src_stride_u;
    (void)src_v; (void)src_stride_v; (void)dst_y; (void)dst_stride_y;
    (void)dst_u; (void)dst_stride_u; (void)dst_v; (void)dst_stride_v;
    (void)width; (void)height;
    return LIBYUV_ERR;
}

int I420ToNV12(const uint8_t* src_y, int src_stride_y,
               const uint8_t* src_u, int src_stride_u,
               const uint8_t* src_v, int src_stride_v,
               uint8_t* dst_y, int dst_stride_y,
               uint8_t* dst_uv, int dst_stride_uv,
               int width, int height) {
    (void)src_y; (void)src_stride_y; (void)src_u; (void)src_stride_u;
    (void)src_v; (void)src_stride_v; (void)dst_y; (void)dst_stride_y;
    (void)dst_uv; (void)dst_stride_uv; (void)width; (void)height;
    return LIBYUV_ERR;
}

int I420ToNV21(const uint8_t* src_y, int src_stride_y,
               const uint8_t* src_u, int src_stride_u,
               const uint8_t* src_v, int src_stride_v,
               uint8_t* dst_y, int dst_stride_y,
               uint8_t* dst_vu, int dst_stride_vu,
               int width, int height) {
    (void)src_y; (void)src_stride_y; (void)src_u; (void)src_stride_u;
    (void)src_v; (void)src_stride_v; (void)dst_y; (void)dst_stride_y;
    (void)dst_vu; (void)dst_stride_vu; (void)width; (void)height;
    return LIBYUV_ERR;
}

int NV12ToI420(const uint8_t* src_y, int src_stride_y,
               const uint8_t* src_uv, int src_stride_uv,
               uint8_t* dst_y, int dst_stride_y,
               uint8_t* dst_u, int dst_stride_u,
               uint8_t* dst_v, int dst_stride_v,
               int width, int height) {
    (void)src_y; (void)src_stride_y; (void)src_uv; (void)src_stride_uv;
    (void)dst_y; (void)dst_stride_y; (void)dst_u; (void)dst_stride_u;
    (void)dst_v; (void)dst_stride_v; (void)width; (void)height;
    return LIBYUV_ERR;
}

int NV21ToI420(const uint8_t* src_y, int src_stride_y,
               const uint8_t* src_vu, int src_stride_vu,
               uint8_t* dst_y, int dst_stride_y,
               uint8_t* dst_u, int dst_stride_u,
               uint8_t* dst_v, int dst_stride_v,
               int width, int height) {
    (void)src_y; (void)src_stride_y; (void)src_vu; (void)src_stride_vu;
    (void)dst_y; (void)dst_stride_y; (void)dst_u; (void)dst_stride_u;
    (void)dst_v; (void)dst_stride_v; (void)width; (void)height;
    return LIBYUV_ERR;
}

void SplitUVPlane(const uint8_t* src_uv, int src_stride_uv,
                  uint8_t* dst_u, int dst_stride_u,
                  uint8_t* dst_v, int dst_stride_v,
                  int width, int height) {
    (void)src_uv;
    (void)src_stride_uv;
    zero_plane(dst_u, dst_stride_u, width, height);
    zero_plane(dst_v, dst_stride_v, width, height);
}

int ARGBToI420(const uint8_t* src_argb, int src_stride_argb,
               uint8_t* dst_y, int dst_stride_y,
               uint8_t* dst_u, int dst_stride_u,
               uint8_t* dst_v, int dst_stride_v,
               int width, int height) {
    (void)src_argb; (void)src_stride_argb; (void)dst_y; (void)dst_stride_y;
    (void)dst_u; (void)dst_stride_u; (void)dst_v; (void)dst_stride_v;
    (void)width; (void)height;
    return LIBYUV_ERR;
}

int ARGBToNV12(const uint8_t* src_argb, int src_stride_argb,
               uint8_t* dst_y, int dst_stride_y,
               uint8_t* dst_uv, int dst_stride_uv,
               int width, int height) {
    (void)src_argb; (void)src_stride_argb; (void)dst_y; (void)dst_stride_y;
    (void)dst_uv; (void)dst_stride_uv; (void)width; (void)height;
    return LIBYUV_ERR;
}

int ABGRToI420(const uint8_t* src_abgr, int src_stride_abgr,
               uint8_t* dst_y, int dst_stride_y,
               uint8_t* dst_u, int dst_stride_u,
               uint8_t* dst_v, int dst_stride_v,
               int width, int height) {
    (void)src_abgr; (void)src_stride_abgr; (void)dst_y; (void)dst_stride_y;
    (void)dst_u; (void)dst_stride_u; (void)dst_v; (void)dst_stride_v;
    (void)width; (void)height;
    return LIBYUV_ERR;
}

int ABGRToNV12(const uint8_t* src_abgr, int src_stride_abgr,
               uint8_t* dst_y, int dst_stride_y,
               uint8_t* dst_uv, int dst_stride_uv,
               int width, int height) {
    (void)src_abgr; (void)src_stride_abgr; (void)dst_y; (void)dst_stride_y;
    (void)dst_uv; (void)dst_stride_uv; (void)width; (void)height;
    return LIBYUV_ERR;
}

int ARGBToABGR(const uint8_t* src_argb, int src_stride_argb,
               uint8_t* dst_abgr, int dst_stride_abgr,
               int width, int height) {
    (void)src_argb; (void)src_stride_argb; (void)dst_abgr; (void)dst_stride_abgr;
    (void)width; (void)height;
    return LIBYUV_ERR;
}

int ABGRToARGB(const uint8_t* src_abgr, int src_stride_abgr,
               uint8_t* dst_argb, int dst_stride_argb,
               int width, int height) {
    (void)src_abgr; (void)src_stride_abgr; (void)dst_argb; (void)dst_stride_argb;
    (void)width; (void)height;
    return LIBYUV_ERR;
}

int RGB24ToI420(const uint8_t* src_rgb24, int src_stride_rgb24,
                uint8_t* dst_y, int dst_stride_y,
                uint8_t* dst_u, int dst_stride_u,
                uint8_t* dst_v, int dst_stride_v,
                int width, int height) {
    (void)src_rgb24; (void)src_stride_rgb24; (void)dst_y; (void)dst_stride_y;
    (void)dst_u; (void)dst_stride_u; (void)dst_v; (void)dst_stride_v;
    (void)width; (void)height;
    return LIBYUV_ERR;
}

int RAWToI420(const uint8_t* src_raw, int src_stride_raw,
              uint8_t* dst_y, int dst_stride_y,
              uint8_t* dst_u, int dst_stride_u,
              uint8_t* dst_v, int dst_stride_v,
              int width, int height) {
    (void)src_raw; (void)src_stride_raw; (void)dst_y; (void)dst_stride_y;
    (void)dst_u; (void)dst_stride_u; (void)dst_v; (void)dst_stride_v;
    (void)width; (void)height;
    return LIBYUV_ERR;
}

int RGB24ToARGB(const uint8_t* src_rgb24, int src_stride_rgb24,
                uint8_t* dst_argb, int dst_stride_argb,
                int width, int height) {
    (void)src_rgb24; (void)src_stride_rgb24; (void)dst_argb; (void)dst_stride_argb;
    (void)width; (void)height;
    return LIBYUV_ERR;
}

int RAWToARGB(const uint8_t* src_raw, int src_stride_raw,
              uint8_t* dst_argb, int dst_stride_argb,
              int width, int height) {
    (void)src_raw; (void)src_stride_raw; (void)dst_argb; (void)dst_stride_argb;
    (void)width; (void)height;
    return LIBYUV_ERR;
}

int I420ToRGB24(const uint8_t* src_y, int src_stride_y,
                const uint8_t* src_u, int src_stride_u,
                const uint8_t* src_v, int src_stride_v,
                uint8_t* dst_rgb24, int dst_stride_rgb24,
                int width, int height) {
    (void)src_y; (void)src_stride_y; (void)src_u; (void)src_stride_u;
    (void)src_v; (void)src_stride_v; (void)dst_rgb24; (void)dst_stride_rgb24;
    (void)width; (void)height;
    return LIBYUV_ERR;
}

int I420ToARGB(const uint8_t* src_y, int src_stride_y,
               const uint8_t* src_u, int src_stride_u,
               const uint8_t* src_v, int src_stride_v,
               uint8_t* dst_argb, int dst_stride_argb,
               int width, int height) {
    (void)src_y; (void)src_stride_y; (void)src_u; (void)src_stride_u;
    (void)src_v; (void)src_stride_v; (void)dst_argb; (void)dst_stride_argb;
    (void)width; (void)height;
    return LIBYUV_ERR;
}

int H444ToARGB(const uint8_t* src_y, int src_stride_y,
               const uint8_t* src_u, int src_stride_u,
               const uint8_t* src_v, int src_stride_v,
               uint8_t* dst_argb, int dst_stride_argb,
               int width, int height) {
    (void)src_y; (void)src_stride_y; (void)src_u; (void)src_stride_u;
    (void)src_v; (void)src_stride_v; (void)dst_argb; (void)dst_stride_argb;
    (void)width; (void)height;
    return LIBYUV_ERR;
}

int NV12ToRGB24(const uint8_t* src_y, int src_stride_y,
                const uint8_t* src_uv, int src_stride_uv,
                uint8_t* dst_rgb24, int dst_stride_rgb24,
                int width, int height) {
    (void)src_y; (void)src_stride_y; (void)src_uv; (void)src_stride_uv;
    (void)dst_rgb24; (void)dst_stride_rgb24; (void)width; (void)height;
    return LIBYUV_ERR;
}

int NV12ToARGB(const uint8_t* src_y, int src_stride_y,
               const uint8_t* src_uv, int src_stride_uv,
               uint8_t* dst_argb, int dst_stride_argb,
               int width, int height) {
    (void)src_y; (void)src_stride_y; (void)src_uv; (void)src_stride_uv;
    (void)dst_argb; (void)dst_stride_argb; (void)width; (void)height;
    return LIBYUV_ERR;
}

int YUY2ToARGB(const uint8_t* src_yuy2, int src_stride_yuy2,
               uint8_t* dst_argb, int dst_stride_argb,
               int width, int height) {
    (void)src_yuy2; (void)src_stride_yuy2; (void)dst_argb; (void)dst_stride_argb;
    (void)width; (void)height;
    return LIBYUV_ERR;
}

int UYVYToARGB(const uint8_t* src_uyvy, int src_stride_uyvy,
               uint8_t* dst_argb, int dst_stride_argb,
               int width, int height) {
    (void)src_uyvy; (void)src_stride_uyvy; (void)dst_argb; (void)dst_stride_argb;
    (void)width; (void)height;
    return LIBYUV_ERR;
}

int ARGBToRGB24(const uint8_t* src_argb, int src_stride_argb,
                uint8_t* dst_rgb24, int dst_stride_rgb24,
                int width, int height) {
    (void)src_argb; (void)src_stride_argb; (void)dst_rgb24; (void)dst_stride_rgb24;
    (void)width; (void)height;
    return LIBYUV_ERR;
}

int ARGBToRAW(const uint8_t* src_argb, int src_stride_argb,
              uint8_t* dst_raw, int dst_stride_raw,
              int width, int height) {
    (void)src_argb; (void)src_stride_argb; (void)dst_raw; (void)dst_stride_raw;
    (void)width; (void)height;
    return LIBYUV_ERR;
}

int MJPGToI420(const uint8_t* sample, size_t sample_size,
               uint8_t* dst_y, int dst_stride_y,
               uint8_t* dst_u, int dst_stride_u,
               uint8_t* dst_v, int dst_stride_v,
               int src_width, int src_height,
               int dst_width, int dst_height) {
    (void)sample; (void)sample_size; (void)dst_y; (void)dst_stride_y;
    (void)dst_u; (void)dst_stride_u; (void)dst_v; (void)dst_stride_v;
    (void)src_width; (void)src_height; (void)dst_width; (void)dst_height;
    return LIBYUV_ERR;
}

int MJPGToNV12(const uint8_t* sample, size_t sample_size,
               uint8_t* dst_y, int dst_stride_y,
               uint8_t* dst_uv, int dst_stride_uv,
               int src_width, int src_height,
               int dst_width, int dst_height) {
    (void)sample; (void)sample_size; (void)dst_y; (void)dst_stride_y;
    (void)dst_uv; (void)dst_stride_uv; (void)src_width; (void)src_height;
    (void)dst_width; (void)dst_height;
    return LIBYUV_ERR;
}

int MJPGToARGB(const uint8_t* sample, size_t sample_size,
               uint8_t* dst_argb, int dst_stride_argb,
               int src_width, int src_height,
               int dst_width, int dst_height) {
    (void)sample; (void)sample_size; (void)dst_argb; (void)dst_stride_argb;
    (void)src_width; (void)src_height; (void)dst_width; (void)dst_height;
    return LIBYUV_ERR;
}

int MJPGSize(const uint8_t* sample, size_t sample_size,
             int* width, int* height) {
    (void)sample;
    (void)sample_size;
    if (width) {
        *width = 0;
    }
    if (height) {
        *height = 0;
    }
    return LIBYUV_ERR;
}

int I420Scale(const uint8_t* src_y, int src_stride_y,
              const uint8_t* src_u, int src_stride_u,
              const uint8_t* src_v, int src_stride_v,
              int src_width, int src_height,
              uint8_t* dst_y, int dst_stride_y,
              uint8_t* dst_u, int dst_stride_u,
              uint8_t* dst_v, int dst_stride_v,
              int dst_width, int dst_height,
              enum FilterMode filtering) {
    (void)src_y; (void)src_stride_y; (void)src_u; (void)src_stride_u;
    (void)src_v; (void)src_stride_v; (void)src_width; (void)src_height;
    (void)dst_y; (void)dst_stride_y; (void)dst_u; (void)dst_stride_u;
    (void)dst_v; (void)dst_stride_v; (void)dst_width; (void)dst_height;
    (void)filtering;
    return LIBYUV_ERR;
}

int NV12Scale(const uint8_t* src_y, int src_stride_y,
              const uint8_t* src_uv, int src_stride_uv,
              int src_width, int src_height,
              uint8_t* dst_y, int dst_stride_y,
              uint8_t* dst_uv, int dst_stride_uv,
              int dst_width, int dst_height,
              enum FilterMode filtering) {
    (void)src_y; (void)src_stride_y; (void)src_uv; (void)src_stride_uv;
    (void)src_width; (void)src_height; (void)dst_y; (void)dst_stride_y;
    (void)dst_uv; (void)dst_stride_uv; (void)dst_width; (void)dst_height;
    (void)filtering;
    return LIBYUV_ERR;
}

int ARGBScale(const uint8_t* src_argb, int src_stride_argb,
              int src_width, int src_height,
              uint8_t* dst_argb, int dst_stride_argb,
              int dst_width, int dst_height,
              enum FilterMode filtering) {
    (void)src_argb; (void)src_stride_argb; (void)src_width; (void)src_height;
    (void)dst_argb; (void)dst_stride_argb; (void)dst_width; (void)dst_height;
    (void)filtering;
    return LIBYUV_ERR;
}

int I420Rotate(const uint8_t* src_y, int src_stride_y,
               const uint8_t* src_u, int src_stride_u,
               const uint8_t* src_v, int src_stride_v,
               uint8_t* dst_y, int dst_stride_y,
               uint8_t* dst_u, int dst_stride_u,
               uint8_t* dst_v, int dst_stride_v,
               int width, int height,
               enum RotationMode mode) {
    (void)src_y; (void)src_stride_y; (void)src_u; (void)src_stride_u;
    (void)src_v; (void)src_stride_v; (void)dst_y; (void)dst_stride_y;
    (void)dst_u; (void)dst_stride_u; (void)dst_v; (void)dst_stride_v;
    (void)width; (void)height; (void)mode;
    return LIBYUV_ERR;
}

int NV12ToI420Rotate(const uint8_t* src_y, int src_stride_y,
                     const uint8_t* src_uv, int src_stride_uv,
                     uint8_t* dst_y, int dst_stride_y,
                     uint8_t* dst_u, int dst_stride_u,
                     uint8_t* dst_v, int dst_stride_v,
                     int width, int height,
                     enum RotationMode mode) {
    (void)src_y; (void)src_stride_y; (void)src_uv; (void)src_stride_uv;
    (void)dst_y; (void)dst_stride_y; (void)dst_u; (void)dst_stride_u;
    (void)dst_v; (void)dst_stride_v; (void)width; (void)height; (void)mode;
    return LIBYUV_ERR;
}

int I420Copy(const uint8_t* src_y, int src_stride_y,
             const uint8_t* src_u, int src_stride_u,
             const uint8_t* src_v, int src_stride_v,
             uint8_t* dst_y, int dst_stride_y,
             uint8_t* dst_u, int dst_stride_u,
             uint8_t* dst_v, int dst_stride_v,
             int width, int height) {
    (void)src_y; (void)src_stride_y; (void)src_u; (void)src_stride_u;
    (void)src_v; (void)src_stride_v; (void)dst_y; (void)dst_stride_y;
    (void)dst_u; (void)dst_stride_u; (void)dst_v; (void)dst_stride_v;
    (void)width; (void)height;
    return LIBYUV_ERR;
}

int NV12Copy(const uint8_t* src_y, int src_stride_y,
             const uint8_t* src_uv, int src_stride_uv,
             uint8_t* dst_y, int dst_stride_y,
             uint8_t* dst_uv, int dst_stride_uv,
             int width, int height) {
    (void)src_y; (void)src_stride_y; (void)src_uv; (void)src_stride_uv;
    (void)dst_y; (void)dst_stride_y; (void)dst_uv; (void)dst_stride_uv;
    (void)width; (void)height;
    return LIBYUV_ERR;
}
