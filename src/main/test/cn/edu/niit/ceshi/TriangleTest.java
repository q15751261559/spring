package cn.edu.niit.ceshi;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TriangleTest {
    Triangle triangle=new Triangle();
    @Test
    void isTri() {
        assertEquals("等腰三角形",triangle.IsTri(2,2,1));
    }
}