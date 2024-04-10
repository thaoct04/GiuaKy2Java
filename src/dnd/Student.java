/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dnd;

import java.util.Date;

/**
 *
 * @author Thuan Thao
 */
public class Student {
       private String id;
        private String name;
        private String address;
        private Date dateOfBirth;

        public Student(String id, String name, String address, Date dateOfBirth) {
            this.id = id;
            this.name = name;
            this.address = address;
            this.dateOfBirth = dateOfBirth;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getAddress() {
            return address;
        }

        public Date getDateOfBirth() {
            return dateOfBirth;
        }
}
