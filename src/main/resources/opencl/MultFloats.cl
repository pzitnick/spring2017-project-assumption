__kernel void mult_floats(__global const float* input, __global float* output) {
    int i = get_global_id(0);
    output[i] = input[i] * input[i];
}