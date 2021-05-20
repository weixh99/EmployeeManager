import java.io.BufferedReader; //缓冲流读取
import java.io.BufferedWriter; //缓冲流输出
import java.io.File; //输入输出文件流
import java.io.FileNotFoundException; //找不到文件异常
import java.io.FileReader; //文件输入流
import java.io.FileWriter; //文件输出流
import java.io.IOException; //检测输入输出异常
import java.text.ParseException; //解析异常，日期或者字符串格式化会抛出这个异常
import java.text.SimpleDateFormat; //日期自定义格式转换
import java.util.Date; //引入Date类
import java.util.Scanner; //引入Scanner类
// 员工类
class Employee {
    private String name; //姓名
    private boolean sex; //性别,true为男,false为女
    private Date brith;	//出生日期
    private String workDays; //工作年月
    private String education; //学历
    private String duty;	//ְ职务
    private String address;	//住址סַ
    private String tel;		//电话
    // Employee 方法
    public Employee(String name, boolean sex, Date brith, String workDays, String education, String duty,String address, String tel) {
        this.name = name;
        this.sex = sex;
        this.brith = brith;
        this.workDays = workDays;
        this.education = education;
        this.duty = duty;
        this.address = address;
        this.tel = tel;
    }
    public String getName() { //获取姓名
        return name;
    }
    public void setName(String name) { //设置姓名
        this.name = name;
    }
    public String getSex() { //获取性别
        if(sex)
            return "男";
        return "女";
    }
    public void setSex(boolean sex) { //设置性别：男或女
        this.sex=sex;
    }
    public Date getBrith() { //获取出生年月
        return brith;
    }
    public void setBrith(Date brith) { //设置出生年月
        this.brith = brith;
    }
    public String getWorkDays() { //获取工作年月
        return workDays;
    }
    public void setWorkDays(String workDays) { //设置工作年月
        this.workDays = workDays;
    }
    public String getEducation() { //获取学历
        return education;
    }
    public void setEducation(String education) { //设置学历
        this.education = education;
    }
    public String getDuty() { //获取职务
        return duty;
    }
    public void setDuty(String duty) { //设置职务
        this.duty = duty;
    }
    public String getAddress() { //获取住址
        return address;
    }
    public void setAddress(String address) { //设置住址
        this.address = address;
    }
    public String getTel() { //获取电话
        return tel;
    }
    public void setTel(String tel) { //设置电话
        this.tel = tel;
    }
}
// 员工管理类
public class EmployeeManager {
    int max = 100; //设置最大存储容量
    int size = 0; //人数
    String fileName = "data.txt"; //默认存储的文件路径
    Employee[] employees = new Employee[max]; //所有的职工
    boolean debug=false;	//是否显示Debug信息（改为boolean debug=true;即为显示Debug信息）
    Scanner scan = new Scanner(System.in);
    // 格式化日期
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); //格式为四位年-月-日
    // Debug函数
    void Debug(String s) {
        if(debug) { //如果有debug，则输出debug
            System.out.println("Debug : "+s);
        }
    }
    // 复制original数组的newLen长度的数组
    Employee[] copyOf(Employee[] original, int newLen) { //在原数组中截取长度为newLen的部分
        Employee[] es = new Employee[newLen];
        for (int i = 0; i < newLen; i++) {
            es[i] = original[i];
        }
        return es; //返回截取的那部分数组
    }
    // 显示参数中的职工信息
    void print(Employee[] es) {
        if (es==null || es.length == 0) { //截取的数组为空或数组长度为0
            System.out.println("没有信息"); //输出"没有信息"
            return;
        }
        System.out.println("姓名\t性别\t出生年月\t\t工作年月\t\t学历\t职务\t住址\t电话"); //否则就输出员工的信息
        for (Employee e : es) {
            System.out.println(
                    e.getName() + "\t" + e.getSex() + "\t" + format.format(e.getBrith()) + "\t" + e.getWorkDays() + "\t"
                            + e.getEducation() + "\t" + e.getDuty() + "\t" + e.getAddress() + "\t" + e.getTel());
        } //获取到员工信息
        System.out.println("共" + es.length + "条信息"); //输出"共n条信息",n为截取的数组的长度
    }
    // 按姓名查询员工
    Integer findByName(String name) {
        for (int i = 0; i < size; i++) {
            if (name.equals(employees[i].getName()))
                return i;
        }
        return null;
    }
    // 按学历查询员工
    Employee[] findByEducation(String education) {
        int count = 0;
        Employee[] es = new Employee[size];
        for (int i = 0; i < size; i++) {
            if (education.equals(employees[i].getEducation())) {
                es[count++] = employees[i];
            }
        }
        return copyOf(es, count); //返回在es数组中截取的长度为count的部分
    }
    // 按职务查询员工
    Employee[] findByDuty(String duty) {
        int count = 0;
        Employee[] es = new Employee[size];
        for (int i = 0; i < size; i++) {
            if (duty.equals(employees[i].getDuty())) {
                es[count++] = employees[i];
            }
        }
        return copyOf(es, count); //返回在es数组中截取的长度为count的部分
    }
    // 按住址查询员工
    Employee[] findByAddress(String address) {
        int count = 0;
        Employee[] es = new Employee[size];
        for (int i = 0; i < size; i++) {
            if (address.equals(employees[i].getAddress())) {
                es[count++] = employees[i];
            }
        }
        return copyOf(es, count); //返回在es数组中截取的长度为count的部分
    }
    // 按电话查询员工
    Employee[] findByTel(String tel) {
        int count = 0;
        Employee[] es = new Employee[size];
        for (int i = 0; i < size; i++) {
            if (tel.equals(employees[i].getTel())) {
                es[count++] = employees[i];
            }
        }
        return copyOf(es, count); //返回在es数组中截取的长度为count的部分
    }
    // 按出生日期查询员工
    Employee[] findByDate(String date1, String date2) throws ParseException { //输入的日期会自动检查格式并抛出异常
        int count = 0;
        Employee[] es = new Employee[size];
        Date d1 = format.parse(date1); //开始日期date1，检查格式抛出异常
        Date d2 = format.parse(date2); //结束日期date2，检查格式抛出异常
        for (int i = 0; i < size; i++) {
            if (employees[i].getBrith().after(d1) && employees[i].getBrith().before(d2)) { //晚于开始日期，早于结束日期
                es[count++] = employees[i];
            }
        }
        return copyOf(es, count); //返回在es数组中截取的长度为count的部分
    }
    // 按性别查询员工
    Employee[] findBySex(String sex) {
        if(!sex.equals("男") && !sex.equals("女"))
            return null;
        int count = 0;
        Employee[] es = new Employee[size];
        for (int i = 0; i < size; i++) {
            if (sex.equals(employees[i].getSex())) {
                es[count++] = employees[i];
            }
        }
        return copyOf(es, count); //返回在es数组中截取的长度为count的部分
    }
    // 添加到数组
    String addToArray(String name, String sex, String brith, String workDays, String education, String duty,String address, String tel) {
        String t; // 临时字符串
        boolean sex1; // 性别,true为男,false为女
        Date brith1; // 出生日期
        // 处理性别
        if (sex.equals("男"))
            sex1 = true;
        else if (sex.equals("女"))
            sex1 = false;
        else
            return "性别错误";
        // 处理日期
        try {  //监听
            brith1 = format.parse(brith);
        } catch (ParseException e) { //格式错误就抛出异常
            return "日期格式错误,示例格式 : 2003-02-12";
        }
        // 添加
        if (size == max - 1)  //当超出容量上限时
            return "员工人数过多，添加失败"; //返回"员工人数过多，添加失败"
        Employee e = new Employee(name, sex1, brith1, workDays, education, duty, address, tel);
        // 按字典序添加到数组中（插入排序）
        int i;
        for (i = 0; i < size; i++) {
            if (e.getName().compareTo(employees[i].getName()) < 0) { //新添加的员工姓名与已有的员工姓名比较
                for (int j = size - 1; j >= i; j--) {
                    employees[j + 1] = employees[j];
                }
                employees[i] = e;
                break;
            }
        }
        if (i == size) {
            employees[size] = e;
        }
        size++;
        return "添加成功";
    }
    // 删除职工
    String del(String name) {
        Integer ie=findByName(name);
        if(ie==null) {
            return "不存在该员工";
        }
        else {
            for (int j = ie; j < size-1; j++) {
                employees[j ] = employees[j+1];
            }
            size--;
        }
        return "删除成功";
    }
    // 修改出生年月
    String changeDate(String name,String date) {
        Integer ie=findByName(name); //先通过姓名查找员工
        if(ie==null) {
            return "不存在该员工";
        }
        else {
            Employee e=employees[ie];
            try {
                Date brith=format.parse(date);
                e.setBrith(brith);
            } catch (ParseException e1) {
                // TODO Auto-generated catch block
                return "日期格式错误";
            }
        }
        return "修改成功";
    }
    // 修改学历
    String changeEducation(String name,String education) {
        Integer ie=findByName(name);  //先通过姓名查找员工
        if(ie==null) {
            return "不存在该员工";
        }
        else {
            Employee e=employees[ie];
            e.setEducation(education);
        }
        return "修改成功";
    }
    // 修改职务
    String changeDuty(String name,String duty) {
        Integer ie=findByName(name);  //先通过姓名查找员工
        if(ie==null) {
            return "不存在该员工";
        }
        else {
            Employee e=employees[ie];
            e.setDuty(duty);
        }
        return "修改成功";
    }
    // 修改住址
    String changeAddress(String name,String address) {
        Integer ie=findByName(name);  //先通过姓名查找员工
        if(ie==null) {
            return "不存在该员工";
        }
        else {
            Employee e=employees[ie];
            e.setAddress(address);
        }
        return "修改成功";
    }
    // 修改电话
    String changeTel(String name,String tel) {
        Integer ie=findByName(name);  //先通过姓名查找员工
        if(ie==null) {
            return "不存在该员工";
        }
        else {
            Employee e=employees[ie];
            e.setTel(tel);
        }
        return "修改成功";
    }
    // 在文件中读取数据
    void read() {
        BufferedReader reader;
        File file;
        try {
            file = new File(fileName);
            if (!file.exists()) {   //文件不存在时
                file.createNewFile(); //创建一个新文件
                return;
            }
            reader = new BufferedReader(new FileReader(file));
            String line; // 一行数据
            while ((line = reader.readLine()) != null) {
                @SuppressWarnings("resource")
                Scanner scans = new Scanner(line);
                String name = scans.next(); //姓名
                String sex = scans.next(); // 性别,true为男,false为女
                String brith = scans.next(); // 出生日期
                String workDays = scans.next(); // 工作年月
                String education = scans.next(); // 学历
                String duty = scans.next(); // ְ职务
                String address = scans.next(); // 地址סַ
                String tel = scans.next(); // 电话
                addToArray(name, sex, brith, workDays, education, duty, address, tel);
            }
            reader.close(); //读取结束
        } catch (FileNotFoundException e) {   //找不到文件异常
            // TODO Auto-generated catch block
            e.printStackTrace(); //在命令行打印异常信息在程序中出错的位置及原因
        } catch (IOException e) { //检测输入输出异常
            // TODO Auto-generated catch block
            e.printStackTrace(); //在命令行打印异常信息在程序中出错的位置及原因
        }
    }
    // 保存到文件
    void save() {
        BufferedWriter write;
        try {
            write = new BufferedWriter(new FileWriter(fileName));
            for (int i = 0; i < size; i++) {   //从第一个开始将每一条员工信息的所有属性写入一个文本text
                Employee e = employees[i];
                String text = e.getName() + "\t" + e.getSex() + "\t" + format.format(e.getBrith()) + "\t"
                        + e.getWorkDays() + "\t" + e.getEducation() + "\t" + e.getDuty() + "\t" + e.getAddress() + "\t"
                        + e.getTel();
                write.write(text); //将text写入文件
                write.newLine(); //继续写入下一行的员工信息
            }
            write.close(); //写入文件结束
        } catch (IOException e) { //检测输入输出异常
            // TODO Auto-generated catch block
            e.printStackTrace(); //在命令行打印异常信息在程序中出错的位置及原因
        }
    }
    // 输出帮助信息
    void help() {
        System.out.println("**");
        System.out.println("* 添加职工");
        System.out.println("* 	add 姓名 性别 出生年月 工作年月 学历 职务 住址 电话");
        System.out.println("* 删除职工");
        System.out.println("* 	del 姓名");
        System.out.println("* 查询职工");
        System.out.println("* 	显示所有职工");
        System.out.println("* 		query");
        System.out.println("* 	按姓名查询");
        System.out.println("*		query -n 姓名");
        System.out.println("* 	按出生日期查询");
        System.out.println("* 		query -b 开始日期 结束日期");
        System.out.println("*       按学历查询");
        System.out.println("*               query -e 学历");
        System.out.println("*       按职务查询");
        System.out.println("*               query -d 职务");
        System.out.println("*       按住址查询");
        System.out.println("*               query -a 住址");
        System.out.println("*       按电话查询");
        System.out.println("*               query -t 电话");
        System.out.println("* 	按性别查询");
        System.out.println("* 		query -s 性别");
        System.out.println("* 修改职工");
        System.out.println("* 	修改出生年月");
        System.out.println("*		change -b 姓名 修改后的出生年月");
        System.out.println("*       修改学历");
        System.out.println("*               change -e 姓名 修改后的学历");
        System.out.println("*       修改职务");
        System.out.println("*               change -d 姓名 修改后的职务");
        System.out.println("*       修改住址");
        System.out.println("*               change -a 姓名 修改后的住址");
        System.out.println("*	修改电话");
        System.out.println("*		change -t 姓名 修改后的电话");
        System.out.println("* 退出");
        System.out.println("* 	exit");
        System.out.println("**");
    }
    //分析输入命令
    void shell() {
        read();
        String cmd;
        System.out.println("欢迎使用员工管理系统，输入help以查看帮助"); //输出提示信息
        while (true) {
            System.out.print("# ");
            cmd = scan.nextLine(); //再读取一行数据赋给cmd
            cmd = cmd.trim(); //去除字符串cmd的前缀和后缀空格
            String[] cmds = cmd.split("( |\t)"); //将字符串分割开来，存入数组
            Debug("cmd= "+cmd);  //输出Debug信息：字符串cmd的值
            Debug("cmds.length= "+cmds.length); //输出Debug信息：数组的长度
            Debug("cmds[0]= "+cmds[0]); //输出Debug信息：数组首元素的值
            // 帮助
            if (cmds[0].equals("help")) { //输入help时
                Debug("help"); //输出Debug信息"help"
                help(); //输出帮助信息
            }
            // 添加
            else if (cmds[0].equals("add")) {
                Debug("add");
                if(cmds.length==9) {
                    Debug("addToArray");
                    System.out.println(addToArray(cmds[1], cmds[2], cmds[3], cmds[4], cmds[5], cmds[6], cmds[7], cmds[8])); //把输入的八个属性（命令）都加入到数组

                }
                else {
                    Debug("命令错误");
                    System.out.println("输入的命令有误，输入help以查看帮助");
                }
            }
            // 删除
            else if (cmds[0].equals("del")) {
                Debug("del");
                if(cmds.length==2)
                    System.out.println(del(cmds[1]));
                else
                    System.out.println("输入的命令有误，输入help以查看帮助");
            }
            // 查询
            else if (cmds[0].equals("query")) {
                Debug("query");
                // 显示所有
                if (cmds.length == 1) {
                    Employee es[] = new Employee[size];
                    for (int i = 0; i < size; i++)
                        es[i] = employees[i];
                    print(es);
                } else if (cmds.length >= 2) {
                    //按姓名查询
                    if (cmds[1].equals("-n")) {
                        if (cmds.length == 3) {
                            Integer i = findByName(cmds[2]);
                            if (i != null) {
                                Employee es[] = { employees[i] };
                                print(es);
                            } else {
                                System.out.println("没有找到该信息");
                            }
                        } else {
                            System.out.println("输入的命令有误，输入help以查看帮助");
                        }
                    }
                    //按学历查询
                    else if (cmds[1].equals("-e")) {
                        if (cmds.length == 3) {
                            Employee[] es;
                            es = findByEducation(cmds[2]);
                            print(es);
                        } else {
                            System.out.println("输入的命令有误，输入help以查看帮助");
                        }
                    }
                    //按职务查询
                    else if (cmds[1].equals("-d")) {
                        if (cmds.length == 3) {
                            Employee[] es;
                            es = findByDuty(cmds[2]);
                            print(es);
                        } else {
                            System.out.println("输入的命令有误，输入help以查看帮助");
                        }
                    }
                    //按住址查询
                    else if (cmds[1].equals("-a")) {
                        if (cmds.length == 3) {
                            Employee[] es;
                            es = findByAddress(cmds[2]);
                            print(es);
                        } else {
                            System.out.println("输入的命令有误，输入help以查看帮助");
                        }
                    }
                    //按电话查询
                    else if (cmds[1].equals("-t")) {
                        if (cmds.length == 3) {
                            Employee[] es;
                            es = findByTel(cmds[2]);
                            print(es);
                        } else {
                            System.out.println("输入的命令有误，输入help以查看帮助");
                        }
                    }
                    //按出生日期查询
                    else if (cmds[1].equals("-b")) {
                        if (cmds.length == 4) {
                            Employee[] es;
                            try {
                                es = findByDate(cmds[2], cmds[3]);
                                print(es);
                            } catch (ParseException e) {
                                System.out.println("日期格式错误");
                            }
                        } else {
                            System.out.println("输入的命令有误，输入help以查看帮助");
                        }
                    }
                    //按性别查询
                    else if (cmds[1].equals("-s")) {
                        if (cmds.length == 3) {
                            Employee[] es;
                            es = findBySex(cmds[2]);
                            print(es);
                        } else {
                            System.out.println("输入的命令有误，输入help以查看帮助");
                        }
                    }
                    else {
                        System.out.println("输入的命令有误，输入help以查看帮助");
                    }
                }
            }
            // 修改
            else if (cmds[0].equals("change")) {
                Debug("change");
                if(cmds.length>=2) {
                    // 修改出生年月
                    if (cmds[1].equals("-b")) {
                        if (cmds.length == 4) {
                            System.out.println(changeDate(cmds[2], cmds[3])); //cmds[2]指令为输入姓名，cmds[3]指令为输入修改后的出生年月
                        }
                        else {
                            System.out.println("输入的命令有误，输入help以查看帮助");
                        }
                    }
                    // 修改电话
                    else if (cmds[1].equals("-t")) {
                        if (cmds.length == 4) {
                            System.out.println(changeTel(cmds[2], cmds[3])); //cmds[2]指令为输入姓名，cmds[3]指令为输入修改后的电话
                        }
                        else {
                            System.out.println("输入的命令有误，输入help以查看帮助");
                        }
                    }
                    // 修改职务
                    else if (cmds[1].equals("-d")) {
                        if (cmds.length == 4) {
                            System.out.println(changeDuty(cmds[2], cmds[3])); //cmds[2]指令为输入姓名，cmds[3]指令为输入修改后的职务
                        }
                        else {
                            System.out.println("输入的命令有误，输入help以查看帮助");
                        }
                    }
                    // 修改住址
                    else if (cmds[1].equals("-a")) {
                        if (cmds.length == 4) {
                            System.out.println(changeAddress(cmds[2], cmds[3])); //cmds[2]指令为输入姓名，cmds[3]指令为输入修改后的住址
                        }
                        else {
                            System.out.println("输入的命令有误，输入help以查看帮助");
                        }
                    }
                    // 修改学历
                    else if (cmds[1].equals("-e")) {
                        if (cmds.length == 4) {
                            System.out.println(changeEducation(cmds[2], cmds[3])); //cmds[2]指令为输入姓名，cmds[3]指令为输入修改后的学历
                        }
                        else {
                            System.out.println("输入的命令有误，输入help以查看帮助");
                        }
                    }
                    else {
                        System.out.println("输入的命令有误，输入help以查看帮助");
                    }
                }
                else {
                    System.out.println("输入的命令有误，输入help以查看帮助");
                }
            }
            // 退出
            else if (cmds[0].equals("exit")) {
                Debug("exit");
                return;
            }
            else {
                Debug("命令不匹配");
                System.out.println("输入的命令有误，输入help以查看帮助");
            }
            save(); //退出前，将员工信息保存到文件
        }
    }
    public static void main(String[] args) throws ParseException {  //主函数抛出异常
        // TODO Auto-generated method stub
        new EmployeeManager().shell(); //调用shell函数
    }
}