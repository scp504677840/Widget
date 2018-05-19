// IStudentService.aidl
package com.scp.aidlservice;

import com.scp.aidlservice.Person;
import com.scp.aidlservice.Student;


// Declare any non-default types here with import statements

interface IStudentService {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

    int add(int x,int y);

    Person getPerson();

    Student getStudent();

}
