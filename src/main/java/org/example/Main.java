package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Book {
    String title = "default";
    String author = "default";
    String publisher = "default";
    int year = 0;

}

public class Main {
    static ArrayList<Book> library = new ArrayList<>();

    public static void main(String[] args) {
        Main pcl = new Main();
        Scanner scanner = new Scanner(System.in);



        boolean loopLife = true;

        while (loopLife) {
            System.out.println("""
                
                ==================================
                1. Add
                2. Delete
                3. Change
                4. Print
                5. Exit
                ==================================
                """);

            System.out.print(">>> ");
            int opt = scanner.nextInt();
            scanner.nextLine();

            switch (opt) {
                case 1:
                    System.out.println("Masukkan data berikut:");
                    System.out.print("Author: ");
                    String tempAuthor = scanner.nextLine();
                    System.out.print("Judul: ");
                    String tempJudul = scanner.nextLine();
                    System.out.print("Publisher: ");
                    String tempPublisher = scanner.nextLine();
                    System.out.print("Year: ");
                    int tempYear = scanner.nextInt();
                    scanner.nextLine();

                    pcl.doAdd(tempAuthor, tempJudul, tempPublisher, tempYear);

                    break;
                case 2:
                    System.out.println("Choose what do you want to delete by criteria: ");
                    System.out.println("""
                            1. Author
                            2. Title
                            3. Publisher
                            4. Year
                            """);
                    System.out.print(">>> ");
                    int criteria = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter the value: ");
                    System.out.print(">>> ");
                    String value = scanner.nextLine().toLowerCase();
                    pcl.doDelete(criteria, value);
                    break;
                case 3:
                    System.out.println("Enter the title of the book you want to change: ");
                    System.out.print(">>> ");
                    String searchTitle = scanner.nextLine().toLowerCase();



                    pcl.doChange1(searchTitle);
                    break;
                case 4:

                    if (library.isEmpty()) {
                        System.out.println("The library is empty");
                    } else {
                        System.out.println("Print by:");
                        System.out.println("""
                                1. Author
                                2. Title
                                3. Publisher
                                4. Year
                                5. All
                                """);
                        System.out.print(">>> ");
                        int criteria2 = scanner.nextInt();
                        scanner.nextLine();

                        switch (criteria2) {
                            case 5:
                                System.out.println("Author | Title | Publisher | Year");
                                for (Book book : library) {
                                    System.out.println(book.author + "|" + book.title + "|" + book.publisher + "|" + book.year);
                                }
                                break;
                            default:
                                System.out.println("Enter the value: ");
                                System.out.print(">>> ");
                                String value2 = scanner.nextLine().toLowerCase();
                                pcl.doPrint(criteria2, value2);
                                break;
                        }


                    }

                    break;
                case 5:
                    System.out.println("Thank you for using our program");
                    System.out.println("Program ended");
                    loopLife = false;
                    break;
                default:
                    break;
            }
        }



    }

    void toJson() {

        try (FileWriter writer = new FileWriter("dataBuku.db")) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            String jsonResult = gson.toJson(library);
            writer.write(jsonResult);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    void fromJson() {

        try (FileReader reader = new FileReader("dataBuku.db")) {

            

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    void doAdd(String author, String title, String publisher, int year) {
        Book tempBook = new Book();
        tempBook.author = author;
        tempBook.title = title;
        tempBook.publisher = publisher;
        tempBook.year = year;
        library.add(tempBook);
    }

    void doDelete(int criteria, String value) {
        // karna index dari 0, kita pake -1 jadi tanda belum ada pilihan
        int indexToDelete = -1;
        Book book = null;
        for (int i = 0; i < library.size(); i++) {
            book = library.get(i);
            if (criteria == 1 && book.author.toLowerCase().equals(value)) {
                indexToDelete = i;
                break;
            } else if (criteria == 2 && book.title.toLowerCase().equals(value)) {
                indexToDelete = i;
                break;
            } else if (criteria == 3 && book.publisher.toLowerCase().equals(value)) {
                indexToDelete = i;
                break;
            } else if (criteria == 4 && book.year == Integer.parseInt(value)) {
                indexToDelete = i;
                break;
            }
        }

        if (indexToDelete == -1) {
            System.out.println("data not found");
        } else {
            library.remove(indexToDelete);
            System.out.println("book \"" + book.title + "\" has been deleted");
        }
    }

//    void doDelete(int criteria, String value) {
//
//        ArrayList<Book> tempList = new ArrayList<>(library);
//        ArrayList<Book> tempRecList = new ArrayList<>();
//        int i = 0;
//
//        for (Book book : library) {
//            switch (criteria) {
//                case 1:
//                    if (book.author.toLowerCase().equals(value)) {
//                        tempRecList.add(tempList.get(i));
//                    }
//                    break;
//                case 2:
//                    if (book.title.toLowerCase().equals(value)) {
//                        tempRecList.add(tempList.get(i));
//                    }
//                    break;
//                case 3:
//                    if (book.publisher.toLowerCase().equals(value)) {
//                        tempRecList.add(tempList.get(i));
//                    }
//                    break;
//                case 4:
//                    if (book.year == Integer.parseInt(value)) {
//                        tempRecList.add(tempList.get(i));
//                    }
//                    break;
//                default:
//                    System.out.println("Author/Title/Publisher/Year is not found\nProcess failed");
//            }
//            i++;
//        }
//
//        tempList = new ArrayList<>(deleteBookFromList(tempList, tempRecList));
//
//
//        library.clear();
//        library = new ArrayList<>(tempList);
//
//        System.out.println("Book deleted");
//        System.out.println("Book information:");
//
//        System.out.println("Author | Title | Publisher | Year");
//        for (Book book : tempRecList) {
//            System.out.println(book.author + "|" + book.title + "|" + book.publisher + "|" + book.year);
//        }
//
//    }

    List<Book> deleteBookFromList(List<Book> sampleList, List<Book> deleteList) {

        if (!deleteList.isEmpty()) {
            String title = deleteList.get(0).title;

            int i = 0;
            for (Book book : sampleList) {
                if (book.title.equals(title)) {
                    break;
                }
                i++;
            }

            sampleList.remove(i);
            deleteList.remove(i);

            if (deleteList.isEmpty()) {
                return sampleList;
            } else {
                deleteBookFromList(sampleList, deleteList);
            }

        } else {
            return sampleList;
        }

        System.out.println("EXITED HERE: deleteBookFromList end");
        return sampleList;
    }

    void doChange1(String title) {

        Scanner scanner = new Scanner(System.in);
        Book sampleBook;
        String authorValue = "0";
        String titleValue = "0";
        String publisherValue = "0";
        int yearValue = 0;

        int i = 0;
        for (Book book : library) {
            if (library.size() == i) {
                System.out.println("Book not found");
                return;
            }
            if (book.title.toLowerCase().equals(title)) {
                break;
            }
            i++;
        }

        sampleBook = library.get(i);

        System.out.println("Select what field you want to change: (T/F)");
        System.out.println("Author: (True/False)");
        boolean changeAuthor = scanner.nextBoolean();
        System.out.println("Title: (True/False)");
        boolean changeTitle = scanner.nextBoolean();
        System.out.println("Publisher: (True/False)");
        boolean changePublisher = scanner.nextBoolean();
        System.out.println("Year: (True/False)");
        boolean changeYear = scanner.nextBoolean();
        scanner.nextLine();

        if (changeAuthor) {
            System.out.println("Enter the new Author's value: ");
            System.out.print(">>> ");
            authorValue = scanner.nextLine();
        }
        if (changeTitle) {
            System.out.println("Enter the new Title's value: ");
            System.out.print(">>> ");
            titleValue = scanner.nextLine();
        }
        if (changePublisher) {
            System.out.println("Enter the new Publisher's value: ");
            System.out.print(">>> ");
            publisherValue = scanner.nextLine();
        }
        if (changeYear) {
            System.out.println("Enter the new Year's value: ");
            System.out.print(">>> ");
            yearValue = scanner.nextInt();
        }

        doChange2(i, sampleBook, changeAuthor, changeTitle, changePublisher, changeYear, authorValue, titleValue, publisherValue, yearValue);

    }

    void doChange2(int idx, Book tempSample, boolean slot1, boolean slot2, boolean slot3, boolean slot4, String val1, String val2, String val3, int val4) {

        boolean lock = false;
        if (!slot1 && !val1.equals("0")) {
            lock = true;
        }
        if (!slot2 && !val2.equals("0")) {
            lock = true;
        }
        if (!slot3 && !val3.equals("0")) {
            lock = true;
        }
//        if (!slot4 && val4 != 0) {
//            lock = true;
//        }

        if (!lock) {

            if (tempSample == null) {
                System.out.println("ERR! BOOK NOT FOUND");
                return;
            }

            if (slot1) {
                tempSample.author = val1;
            }
            if (slot2) {
                tempSample.title = val2;
            }
            if (slot3) {
                tempSample.publisher = val3;
            }
            if (slot4) {
                tempSample.year = val4;
            }

            library.remove(idx);
            library.add(tempSample);

            System.out.println("\nBook information has been updated!");

        }

    }

    void doPrint(int criteria, String value) {
        ArrayList<Book> printList = new ArrayList<>();

        for (Book book : library) {
            switch (criteria) {
                case 1:
                    if (book.author.toLowerCase().equals(value)) {
                        printList.add(book);
                    }
                    break;
                case 2:
                    if (book.title.toLowerCase().equals(value)) {
                        printList.add(book);
                    }
                    break;
                case 3:
                    if (book.publisher.toLowerCase().equals(value)) {
                        printList.add(book);
                    }
                    break;
                case 4:
                    if (book.year == Integer.parseInt(value)) {
                        printList.add(book);
                    }
                    break;
                default:
                    System.out.println("ERR");
            }
        }

        System.out.println("Author | Title | Publisher | Year");
        for (Book book : printList) {
            System.out.println(book.author + "|" + book.title + "|" + book.publisher + "|" + book.year);
        }

    }

}