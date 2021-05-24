package windowbuilder.common;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Date;
import java.util.List;
public class PROTOCAL {
//路由器个数
static int N=0;
//表示路由器拓扑结构的邻接表
public static ArrayList[] Routers;
//路由器的路由表
public static ArrayList<RIP>[] rips;
public static char error='0';
//路由表
public static class RIP implements Serializable{
	public char name;//代表路由表路由器名字
	public int Net;//目的网络
	public int d;//距离
	public char Next;//下一条路由器
	public boolean is_valid;//是否有效
	public long time;//上次更新时间
	RIP(){
		is_valid=true;
	}
	
}
//读取路由器拓扑结构
public static void ReadRouters(String filename) {
	if(filename==null) {
		throw new IllegalArgumentException("Filename can not be null");

	}
	Scanner scanner=null;
	try {
		File file=new File(filename);
		if(!file.exists()) {
			throw new IllegalArgumentException("File "+ filename+ " doesn't exist");
		}
			FileInputStream fis=new FileInputStream(file);
			scanner =new Scanner(new BufferedInputStream(fis),"UTF-8");
			 N=Integer.parseInt(scanner.nextLine());
			//初始化邻接表
			Routers=new ArrayList[N+1];
			for(int i1=0;i1<=N;i1++) {
				Routers[i1]=new ArrayList();
			}
			//打印出路由器的数量
			//System.out.println("The number of rounters"+N);
			for(int i=1;i<=N;i++) {
				String str[]=scanner.nextLine().split(" ");
				//构建邻接表
				int len=str.length;
				for(int j=1;j<len;j++) {
				Routers[str[0].charAt(0)-'A'+1].add(str[j].charAt(0)-'A'+1);
				}
			}
			
		}
		
	
	catch(IOException e) {
		e.printStackTrace();
	}
	 finally {
         if(scanner != null)
             scanner.close();
     }
}
//读取路由表初始化情况
public static void ReadRips(String filename) {
	if(filename==null) {
		throw new IllegalArgumentException("Filename can not be null");

	}
	Scanner scanner=null;
	try {
		File file=new File(filename);
		if(!file.exists()) {
			throw new IllegalArgumentException("File "+ filename+ " doesn't exist");
		}
			FileInputStream fis=new FileInputStream(file);
			scanner =new Scanner(new BufferedInputStream(fis),"UTF-8");
			rips=new ArrayList[N+1];
			for(int i=0;i<=N;i++) {
				rips[i]=new ArrayList();
			}
			for(int i=1;i<=N;i++) {
				int Net;
				do {
				Net=scanner.nextInt();
				int d=scanner.nextInt();
				char next=scanner.next().charAt(0);
				//路由表里一条记录
				RIP Rp=new RIP();
				Rp.name=(char) ('A'+i-1);
				Rp.Net=Net;
				Rp.d=d;
				Rp.Next=next;
				Rp.time=new Date().getTime();//获取当前时间
				if(Net!=-1) {
				rips[i].add(Rp);
				}
				}while(Net!=-1);
				
		        
				
			}
			
		}
		
	
	catch(IOException e) {
		e.printStackTrace();
	}
	 finally {
         if(scanner != null)
             scanner.close();
     }
}
//打印路由表
public static void PrintRips() {
	ArrayList<RIP> r;
	for(int i=1;i<=N;i++) {
		r=rips[i];
		System.out.printf("***********************路由表%c************************\n",(char)('A'+i-1));
		System.out.printf("到目的网络N\t\t距离d\t\t下一跳路由器X\n");
		for(int j=0;j<r.size();j++) {
			System.out.printf("%d\t\t\t%d\t\t%c\n", r.get(j).Net,r.get(j).d,r.get(j).Next);
		}
	}
}


//打印拓扑结构
public static void printStrucR() {

	//打印N--路由器个数
	System.out.println("The number of routers is "+N);
	//打印路由邻接表
	for(int i=0;i<N;i++) {
	System.out.println("The neighbours of "+ (char)('A'+i));
	int len=Routers[i+1].size();
	for(int i1=0;i1<len;i1++) {
		int ele=(int) Routers[i+1].get(i1);
		if(i1==0) {
			System.out.print((char)('A'+ele-1));
		}
		else {
			System.out.print(" ");
			System.out.print((char)('A'+ele-1));
		}
	}
	System.out.println();
	}
}
//打印查询结果
public static void printQRe(char Router,int Inet) {
	
	int IRouter=Router-'A'+1;
	System.out.printf("the shortest distance between Router %c and Net %d is %d\n", Router,Inet,rips[IRouter].get(Inet-1).d);
	System.out.println("this is the path");
	int count=0;
	while(Router!='-') {
		if(count==0) {
			System.out.print(Router);
		}
		else {
			System.out.print("-->"+Router);
		}
		count++;
		IRouter=Router-'A'+1;
		Router=rips[IRouter].get(Inet-1).Next;
	}
	System.out.println();
}
public static <RIP> List<RIP> deepCopy(List<RIP> src) throws IOException, ClassNotFoundException {  
    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();  
    ObjectOutputStream out = new ObjectOutputStream(byteOut);  
    out.writeObject(src);  
      
    ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());  
    ObjectInputStream in = new ObjectInputStream(byteIn);  
    @SuppressWarnings("unchecked")  
    List<RIP> dest = (List<RIP>) in.readObject();  
    return dest;  
} 
//更新路由表
public static boolean update() throws ClassNotFoundException, IOException {
	//表示更新有无修改，来判断是否收敛
	boolean hasEdit=false;
	ArrayList<RIP> send;
	ArrayList<RIP> rip1[]=new ArrayList[N+1];
	for(int i=1;i<=N;i++) {
		rip1[i]= (ArrayList<RIP>) deepCopy(rips[i]);
	}
	int Ierror=-1;
	//如果出现路由错误的情况
	if(error!='0') {
	Ierror=error-'A'+1;
	}
	//遍历每个路由结点
	for(int i=1;i<=N;i++) {
		//如果路由器不出现错误由路由器 i+'A'-1向邻居结点传送报文
		if(i!=Ierror) {
		send=Send(rips[i],(char)(i+'A'-1));
		
		//遍历第i个路由器的邻居，将新的路由表与send路由表比较
		for(int j=0;j<Routers[i].size();j++) {
			//获取邻居路由器序号
			int index=(int)Routers[i].get(j);
			if(index!=Ierror) {
			//若路由器正常，对send返回过来的报文进行一条一条的插入判断
			for(int x=0;x<send.size();x++) {
				int k;
				//与rip1进行筛查比较
				for(k=0;k<rip1[index].size();k++) {
					//先判断是否存在目的网络，如存在进行比较
					if(send.get(x).Net==rip1[index].get(k).Net) {
						//判断下一跳是否相同，若相同进行update
						if(send.get(x).Next==rip1[index].get(k).Next) {
							//?时间问题
							if(rip1[index].get(k).d!=send.get(x).d) {
							rip1[index].get(k).d=send.get(x).d;
							
							rip1[index].get(k).is_valid=true;
							hasEdit=true;
							}
							rip1[index].get(k).time=send.get(x).time;
						}
					
					//下一跳不同的情况
					else {
						//若收到的项目中的距离d小于路由表的距离则更新否则什么都不做
						if(send.get(x).d<rip1[index].get(k).d) {
							rip1[index].get(k).d=send.get(x).d;
							rip1[index].get(k).time=send.get(x).time;
							rip1[index].get(k).Next=send.get(x).Next;
							rip1[index].get(k).is_valid=true;
							hasEdit=true;
						}
					}
					break;//跳出
				}
				}
				//若没有找到目的网络N,那么就将该项目添加到路由表中
				//时间？
				if(k==rip1[index].size()) {
					rip1[index].add(send.get(x));
					hasEdit=true;
				}
			}
			
		}
		}
		}
		//rips[i]=new ArrayList(rip1[i]);
	}
	//更新rips，写回
	for(int i=1;i<=N;i++) {
		rips[i]=(ArrayList<RIP>) deepCopy(rip1[i]);
		//按照目的网络排序
		MyCompare cmp=new MyCompare();
		Collections.sort(rips[i],cmp);
	}
	return hasEdit;
}
public static class MyCompare implements Comparator<RIP>{

	@Override
	public int compare(RIP x, RIP y) {
		// TODO 自动生成的方法存根
		return x.Net-y.Net;
	}
	
}
//变化路由表 即为距离加一，下一节点设为发送结点，并且相应更新时间
public static ArrayList<RIP> Send(ArrayList<RIP> r,char c){
	for(int i=0;i<r.size();i++) {
		r.get(i).d++;
		r.get(i).Next=c;
		r.get(i).time=new Date().getTime();
	}
	return r;
	
}
public static void check() {
	long t=new Date().getTime();
	for(int i=1;i<=N;i++) {
		for(int j=0;j<rips[i].size();j++) {
			if(rips[i].get(j).is_valid&&(t-rips[i].get(j).time)>180) {
				//180s 无更新 标记无效
				rips[i].get(j).is_valid=false;
}
			else if(!rips[i].get(j).is_valid&&(t-rips[i].get(j).time)>120) {
				//再过120s无更新删除
				rips[i].remove(j);
			}
		}
	}
}
public static void check1(char error) {
	int Ierror=error-'A'+1;
	for(int i=1;i<=N;i++) {
		if(i==Ierror) {
			rips[i].clear();
		}
		else {
			int turn=rips[i].size();
			int rank=0;
			for(int j=0;j<turn;j++) {
				if(rips[i].get(rank).Next==error) {
					rips[i].remove(rank);
				}
				else {
					rank++;
				}
			}
		}
	}
}
	/*public static void main(String[] args) throws ClassNotFoundException, IOException {
		Scanner input=new Scanner(System.in);
		String filename1=input.nextLine();
        String filename2=input.nextLine();
        ReadRouters(filename1);
		printStrucR();
        ReadRips(filename2);
        PrintRips();
        int T=0;
        int n=0;
        		
        //休眠1s
        while(true) {
        	//每间隔1s 更新路由表
        	try {
        		Thread.sleep(1000);
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
        	System.out.printf("\n------------------------第%d次更新------------------------\n",++T);
        	if(update()) {
        		PrintRips();
        		n++;
        	}
        	else {
        		System.out.printf("本次没有任何有价值的修改!说明前面%d次修改后,每一个路由器都得到了全局路由信息!\n",n);
        		//新加手动控制错误
        		System.out.print("请手动添加哪一个路由器出现了问题,若没有问题则输入'0'：");
        		error=input.next().charAt(0);
        		
        		if(error!='0') {
        		System.out.printf("路由器%c发生错误", error);
        		check1(error);
        		System.out.println("显示出现路由器错误的路由表");
        		PrintRips();
        		}
        		
        		else {
        			System.out.printf("\n------------------------已经无错误发生------------------------\n");
        			break;
        		}
        		//break;
        	}
        	//check();
        }
        
	}*/

}
