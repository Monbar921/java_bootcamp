package ex05;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        int LIMIT_INPUT = 10;
        Scanner scanner = new Scanner(System.in);
        run(scanner, LIMIT_INPUT);
        scanner.close();
    }

    private static void run(Scanner scanner, int LIMIT_INPUT) {
        int dot_counter = 0;
        String[] students = new String[LIMIT_INPUT];
        String[] lessons = new String[LIMIT_INPUT];
        String[] attendance = new String[LIMIT_INPUT];
        int array_iterator = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.equals(".")) {
                ++dot_counter;
                array_iterator = 0;
                if (dot_counter != 3) {
                    continue;
                } else {
                    break;
                }
            }
            if (dot_counter == 0) {
                students[array_iterator++] = line;
            } else if (dot_counter == 1) {
                lessons[array_iterator++] = line;
            } else if (dot_counter == 2) {
                attendance[array_iterator++] = line;
            }
        }
        scanner.close();
        printSchedule(students, lessons, attendance, LIMIT_INPUT);
    }


    private static void printSchedule(String[] students, String[] lessons, String[] attendance, int LIMIT_INPUT) {
        int printedLessons = printTimeline(lessons);
        attendance(students, lessons, attendance, LIMIT_INPUT);
    }


    private static int printTimeline(String[] lessons) {
        int printedLessons = 0;
        int dayInMonth = 31;
        int currentDayOfWeek = 6;
        boolean is_printed_timeline = false;
        for (int day = 1; day <= dayInMonth; ++day) {
            for (String lesson : lessons) {
                if (lesson == null) {
                    break;
                }
                String[] timeAndDay = lesson.split(" ");
                int dayOfWeek = dayOfWeekAsNumber(timeAndDay[1]);
                if (dayOfWeek == currentDayOfWeek) {

                    if (!is_printed_timeline) {
                        System.out.printf("%10s", " ");
                        is_printed_timeline = true;
                    }
                    System.out.printf("%s:00 %s %3d|", timeAndDay[0], timeAndDay[1], day);
                    ++printedLessons;
                }
            }
            ++currentDayOfWeek;
            currentDayOfWeek %= 7;
            currentDayOfWeek = currentDayOfWeek != 0 ? currentDayOfWeek : 7;
        }
        System.out.println();
        return printedLessons;
    }


    private static void attendance(String[] students, String[] lessons, String[] attendance, int LIMIT_INPUT) {
        int dayInMonth = 31;
        int studentCount = 0;
        for (String student : students) {
            int currentDayOfWeek = 6;

            if (student == null || studentCount == LIMIT_INPUT) {
                break;
            }
            ++studentCount;

            loopThroughDayInMonth(lessons, attendance, dayInMonth, currentDayOfWeek, student);

            System.out.println();
        }
    }

    private static void loopThroughDayInMonth(String[] lessons, String[] attendance, int dayInMonth, int currentDayOfWeek, String student) {
        boolean is_printed_student = false;
        for (int day = 1; day <= dayInMonth; ++day) {
            for (String lesson : lessons) {
                if (lesson == null) {
                    break;
                }
                String[] timeAndDay = lesson.split(" ");
                int dayOfWeek = dayOfWeekAsNumber(timeAndDay[1]);
                if (dayOfWeek == currentDayOfWeek) {

                    if (!is_printed_student) {
                        System.out.printf("%10s", student);
                        is_printed_student = true;
                    }
                    boolean is_attendance = false;
                    for (String studentAttendance : attendance) {
                        if (studentAttendance == null) {
                            break;
                        }
                        String[] splitAttendance = studentAttendance.split(" ");
                        if (splitAttendance[0].equals(student) && splitAttendance[1].equals(timeAndDay[0]) && splitAttendance[2].equals(day + "")) {

                            System.out.printf("%11d|", splitAttendance[3].equals("HERE") ? 1 : -1);
                            is_attendance = true;
                        }
                    }
                    if (!is_attendance) {
                        System.out.printf("%11s|", " ");
                    }
                }
            }
            ++currentDayOfWeek;
            currentDayOfWeek %= 7;
            currentDayOfWeek = currentDayOfWeek != 0 ? currentDayOfWeek : 7;
        }
    }

    private static int dayOfWeekAsNumber(String day) {
        return switch (day) {
            case "MO" -> 1;
            case "TU" -> 2;
            case "WE" -> 3;
            case "TH" -> 4;
            case "FR" -> 5;
            case "SA" -> 6;
            case "SU" -> 7;
            default -> 0;
        };
    }

}
