package com.example.hw_1403.backend.entities

data class Student(var id: Int, var name: String, var hwCount: Int) {

    companion object Holder {
        fun init(): List<Student> {
            return listOf(
                    Student(1, "Aliaksei Sh", 6),
                    Student(2, "Maryia", 5),
                    Student(3, "Pavel", 5),
                    Student(4, "Yahor Sh", 4),
                    Student(5, "Anton", 4),
                    Student(6, "Yahor B", 4),
                    Student(7, "Maksim Zh", 4),
                    Student(8, "Uladislau", 5),
                    Student(9, "Alexander", 5),
                    Student(10, "Maksim N", 6),
                    Student(11, "Vitali", 4),
                    Student(12, "Aliaksandr", 6),
                    Student(13, "Kiryl", 5),
                    Student(14, "Aleksei", 5),
                    Student(15, "Natallia", 4),
                    Student(16, "Aliaksei H", 5),
                    Student(17, "Maksim S", 6),
                    Student(18, "Vladyslav", 5))
        }
    }
}