package HashTableLeetcode;

import java.util.Scanner;

public class EmployeeAdd {
    public static void main(String[] args) {
        hashManager hashtab_test = new hashManager(7);

        Scanner sc = new Scanner(System.in);


        while (true) {

            System.out.println("add:添加雇员");
            System.out.println("list:显示雇员");
            System.out.println("exit:退去系统");
            System.out.println("find:查找");

            String key;
            key = sc.next();
            switch (key) {
                case "a":
                    System.out.println("input id");
                    int id = sc.nextInt();
                    System.out.println("input name");
                    String name = sc.next();
                    Manager e = new Manager(id, name);
                    hashtab_test.add(e);
                    System.out.println("添加成功");
                    break;

                case "l":
                    hashtab_test.list();
                    break;
                case "e":
                    sc.close();
                    System.exit(0);
                    break;
                case "f":
                    System.out.println("input id");
                    int fid = sc.nextInt();
                    hashtab_test.findByID(fid);
                    break;
            }
        }
    }

    static class hashManager {
        public int size;
        public Manager[] managerList;

        public hashManager(int size) {
            this.size = size;
            managerList = new Manager[size];//构造存放多条链表的数组 一个初始化的数组

//        初始化数组里面的每一个元素 都赋值
            for (int i = 0; i < managerList.length; i++) {
                managerList[i] = new Manager(0, ""); //初始化数组里面的每一个元素 都赋值(这一步的意义？）
            }
        }

        public int LocationOfSequence(int n) {
            return n % size;
        }

        public void add(Manager manager) {
            int location = LocationOfSequence(manager.no);
            managerList[location].addPersonBYOrder(manager);
        }

        public void findByID(int id) {
            managerList[id % size].findByIdInSequence(id);
        }

        public void list()
        {
            for (int i = 0; i < managerList.length; i++) {
                managerList[i].listPerson();
            }
        }

    }

    static class Manager {
        public int no;
        public String name;
        public Manager next;

        public Manager(int no, String name) {
            this.no = no;
            this.name = name;
        }

        @Override
        public String toString() {
            return "Manager{" +
                    "age=" + no +
                    ", name='" + name + '\'' +
                    '}';
        }

        public void addPersonBYOrder(Manager manager_exp) {
            Manager DummyHead = new Manager(0, " ");
            Manager cur = DummyHead;

            while (cur != null)
            {
                if (manager_exp.no > cur.no) {
                    manager_exp.next = cur.next;
                    cur.next = manager_exp;
                    break;
                }
                cur = cur.next;
            }
        }

        public void listPerson() {
            Manager DummyHead = new Manager(0, " ");
            Manager cur = DummyHead;

            while (cur != null) {
                System.out.println(cur.next);
                cur = cur.next;
            }
        }

        public void findByIdInSequence(int id) {
            Manager DummyHead = new Manager(0, " ");
            Manager cur = DummyHead;

            boolean flag = false;
            while (cur != null) {
                if (cur.no == id) {
                    System.out.println(cur); //反正已经重新给写过了
                    flag = true;
                    break;
                }
                cur = cur.next;
            }
            if (!flag) {
                System.out.println("没有找到");
            }
        }

    }
}
