#include <jni.h>

JNIEXPORT jint JNICALL
Java_com_eugene_keyboard_math_AdditionExpression_addition__II(JNIEnv *env, jobject instance,
                                                              jint leftOperand,
                                                              jint rightOperand) {

    return leftOperand + rightOperand;

}

JNIEXPORT jint JNICALL
Java_com_eugene_keyboard_math_SubtractionExpression_subtraction(JNIEnv *env, jobject instance,
                                                                jint leftOperand,
                                                                jint rightOperand) {

    return leftOperand - rightOperand;

}

JNIEXPORT jint JNICALL
Java_com_eugene_keyboard_math_DivisionExpression_division(JNIEnv *env, jobject instance,
                                                          jint leftOperand, jint rightOperand) {

    return leftOperand / rightOperand;

}

JNIEXPORT jint JNICALL
Java_com_eugene_keyboard_math_MultiplicationExpression_multiplication(JNIEnv *env, jobject instance,
                                                                      jint leftOperand,
                                                                      jint rightOperand) {

    return leftOperand * rightOperand;

}