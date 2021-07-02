package com.yhmstu.houserent.view;
import com.yhmstu.houserent.domain.House;
import com.yhmstu.houserent.service.HouseService;
import com.yhmstu.houserent.utils.Utility;

/**
 * 1.显示界面
 * 2.接收用户输入
 * 3.调用HouseService完成房屋信息的各种操作
 */
public class HouseView {
    //编写主菜单
    private char key = ' ';//接收用户的输入
    private boolean loop = true;//用于do while循环的控制
    private HouseService houseService = new HouseService(2);//设置数组大小10

    //编写houseAdd()接受输入，创建House对象，调用add()
    public void addHouse() {
        System.out.println("----------------添加房屋----------------");
        System.out.print("姓名：");
        String name = Utility.readString(8);
        System.out.print("电话：");
        String phone = Utility.readString(12);
        System.out.print("地址：");
        String address = Utility.readString(16);
        System.out.print("月租：");
        int rent = Utility.readInt();
        System.out.print("状态(未出租/已出租)：");
        String state = Utility.readString(8);
        //创建一个新的对象,id由系统分配，用户不能输入
        House house = new House(0, name, phone, address, rent, state);
        if (houseService.add(house)) {
            System.out.println("----------------添加房屋成功----------------");
        } else {
            System.out.println("----------------添加房屋失败----------------");
        }
    }
    //编写searchHouse() 查找房屋信息，接收输入的id，调用service的search方法

    public void searcHouse() {
        System.out.println("----------------查找房屋----------------");
        System.out.print("请输入你要查找的id: ");
        int searchId = Utility.readInt();
        if (searchId == -1) {
            System.out.println("----------------你放弃了查找----------------");
            return;
        }
        houseService.search(searchId);
    }

    //编写delHouse()删除房屋,接受输入的id，调用服务层的delid方法
    public void delHouse() {
        System.out.println("----------------删除房屋----------------");
        System.out.print("请选择待删除房屋编号(-1退出): ");
        int delId = Utility.readInt();
        if (delId == -1) {
            System.out.println("----------------你放弃了删除----------------");
            return;
        }
        char choice = Utility.readConfirmSelection();
        if (choice == 'Y') {
            houseService.del(delId);
        } else if (choice == 'N') {
            System.out.println("----------------你放弃了删除----------------");
        }

    }

    //编写modifyHouse()修改房屋信息
    public void modifyHouse() {
        System.out.println("----------------修改房屋----------------");
        System.out.print("请选择待修改房屋编号(-1退出): ");
        int modId = Utility.readInt();
        if (modId == -1) {
            System.out.println("----------------你放弃了修改----------------");
            return;
        }
        houseService.modify(modId);
    }

    //编写listHouse()显示房屋列表
    public void listHouses() {
        System.out.println("-----------------房屋列表-----------------");
        System.out.println("编号\t房主\t电话\t地址\t月租\t 状态未出租/已出租 ");
        House[] list = houseService.list();
        for (int i = 0; i < list.length; i++) {
            if (list[i] == null) {
                break;
            }
            System.out.println(list[i]);
        }
        System.out.println("-----------------显示完毕-----------------");

    }

    //退出确认
    public void exit() {
        char exit = Utility.readConfirmSelection();
        if (exit == 'Y') {
            loop = false;
        }
    }


    //菜单打印
    public void mainMenu() {
        do {
            System.out.println("----------------房屋出租系统----------------");
            System.out.println("\t\t\t  1 新增房源");
            System.out.println("\t\t\t  2 查找房源");
            System.out.println("\t\t\t  3 删除房源");
            System.out.println("\t\t\t  4 修改房源");
            System.out.println("\t\t\t  5 房源列表");
            System.out.println("\t\t\t  6 退出系统");
            System.out.print("请选择(1-6): ");
            key = Utility.readChar();
            switch (key) {
                case '1':
                    addHouse();
                    break;
                case '2':
                    searcHouse();
                    break;
                case '3':
                    delHouse();
                    break;
                case '4':
                    modifyHouse();
                    break;
                case '5':
                    listHouses();
                    break;
                case '6':
                    exit();
                    break;
            }
        } while (loop);
    }
}
