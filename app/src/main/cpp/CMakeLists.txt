cmake_minimum_required(VERSION 3.18)
project(espeakng-jni)

# Set default flags for Android
set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} -Wall -Wextra -O3")

# Create JNI library
add_library(espeakng-jni SHARED
    espeakng_jni.c
)

# Include eSpeak NG headers (you'll need to add these)
target_include_directories(espeakng-jni PRIVATE
    ${CMAKE_CURRENT_SOURCE_DIR}
)

# Link with log library
target_link_libraries(espeakng-jni
    log
)

# TODO: Link with espeak-ng library when added as submodule
# For now, a stub implementation is provided
