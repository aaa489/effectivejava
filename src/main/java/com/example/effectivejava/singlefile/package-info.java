/*
 * 测试多文件中定义了同一个类的问题
 */

/**
 * 《effective java》-25：将源文件限制为单个顶级类
 * 在源文件中定义多个顶级类使得为类提供多个定义成为可能，使用哪个定义会受到源文件传递给编译器的顺序的影响
 * 见com.example.effectivejava.singlefile.Utensil和com.example.effectivejava.singlefile.Dessert
 * 目前idea做了这一层校验，所以这一点我们不用关心了
 */
package com.example.effectivejava.singlefile;