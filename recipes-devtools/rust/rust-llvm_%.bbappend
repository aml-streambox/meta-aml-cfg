# bindgen requires libclang at build time. Kirkstone's rust-llvm recipe only
# builds LLVM, so enable the Clang project for the native variant and stage
# libclang in the native sysroot for Rust recipes that use bindgen.
EXTRA_OECMAKE:append:class-native = " \
    -DLLVM_ENABLE_PROJECTS=clang \
    -DCLANG_ENABLE_ARCMT=OFF \
    -DCLANG_ENABLE_STATIC_ANALYZER=OFF \
"
