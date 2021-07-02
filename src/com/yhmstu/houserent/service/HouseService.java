package com.yhmstu.houserent.service;

import com.yhmstu.houserent.domain.House;
import com.yhmstu.houserent.utils.Utility;

/**
 * 定义数组House[],保存House对象
 * 响应HouseView的调用
 * crud
 */
public class HouseService {
    private House[] houses;//数组保存houses对象
    private int houseNums = 1; //记录房屋数量
    private int idCounter = 1;

    // new houses
    public HouseService(int size) {
        houses = new House[size];
        houses[0] = new House(1, "jack", "122", "海定区", 2000, "未出租");
    }

    //add方法
    public boolean add(House newHouse) {
        //判断是否可以继续添加(暂时不考虑扩容)
        if (houseNums == houses.length) {//不能添加
            System.out.println("数组已满不可以在添加了");
            return false;
        }
        //把newHouse按顺序放入数组
        houses[houseNums++] = newHouse;
        //设计一个id自增长
        newHouse.setId(++idCounter);
        return true;
    }

    //search,查找房源
    public boolean search(int searchId) {
        //应该判断id号是否存在
        int index = -1;
        for (int i = 0; i < houseNums; i++) {
            if (searchId == houses[i].getId()) {// 要删除的房屋对应的id，在数组下标为i的元素
                index = i;//记录下来i
            }
        }
        if (index == -1) {//说明这个id在数组中不存在
            System.out.println("该房源不存在");
            return false;
        }
        System.out.println(houses[index]);
        System.out.println("-----------------查找完成----------------");
        return true;
    }

    //编写modifyHouse()修改房屋信息
    public boolean modify(int modId) {
        //应该判断id号是否存在
        int flag = -1;
        for (int i = 0; i < houseNums; i++) {
            if (modId == houses[i].getId()) {// 要删除的房屋对应的id，在数组下标为i的元素
                flag = i;//记录下来i
            }
        }
        if (flag == -1) {//说明这个id在数组中不存在
            System.out.println("该房源不存在");
            return false;
        }
        System.out.print("姓名" + "(" + houses[flag].getName() + "): ");
        houses[flag].setName(Utility.readString(10));
        System.out.print("电话" + "(" + houses[flag].getPhone() + "): ");
        houses[flag].setPhone(Utility.readString(10));
        System.out.print("地址" + "(" + houses[flag].getAddress()+ "): ");
        houses[flag].setAddress(Utility.readString(10));
        System.out.print("租金" + "(" + houses[flag].getRent()+ "): ");
        houses[flag].setRent(Utility.readInt(10));
        System.out.print("状态" + "(" + houses[flag].getState() + "): ");
        houses[flag].setState(Utility.readString(10));
        System.out.println("-----------------修改完成----------------");
        return true;

    }

    //del方法,删除一个房屋信息
    public boolean del(int delId) {
        //应该判断id号是否存在
        int index = -1;
        for (int i = 0; i < houseNums; i++) {
            if (delId == houses[i].getId()) {// 要删除的房屋对应的id，在数组下标为i的元素
                index = i;//记录下来i
            }
        }
        if (index == -1) {//说明这个id在数组中不存在
            System.out.println("删除房屋失败,该房源不存在");
            return false;
        }
        //如果找到就删除
        for (int i = index; i < houseNums - 1; i++) {
            houses[i] = houses[i + 1];
        }
        //把当前存在的房屋信息的最后一个滞空
        houses[--houseNums] = null;
        System.out.println("-----------------删除完成----------------");
        return true;
    }

    //lise()返回信息
    public House[] list() {
        return houses;
    }
}
