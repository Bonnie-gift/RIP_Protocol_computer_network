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
//·��������
static int N=0;
//��ʾ·�������˽ṹ���ڽӱ�
public static ArrayList[] Routers;
//·������·�ɱ�
public static ArrayList<RIP>[] rips;
public static char error='0';
//·�ɱ�
public static class RIP implements Serializable{
	public char name;//����·�ɱ�·��������
	public int Net;//Ŀ������
	public int d;//����
	public char Next;//��һ��·����
	public boolean is_valid;//�Ƿ���Ч
	public long time;//�ϴθ���ʱ��
	RIP(){
		is_valid=true;
	}
	
}
//��ȡ·�������˽ṹ
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
			//��ʼ���ڽӱ�
			Routers=new ArrayList[N+1];
			for(int i1=0;i1<=N;i1++) {
				Routers[i1]=new ArrayList();
			}
			//��ӡ��·����������
			//System.out.println("The number of rounters"+N);
			for(int i=1;i<=N;i++) {
				String str[]=scanner.nextLine().split(" ");
				//�����ڽӱ�
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
//��ȡ·�ɱ��ʼ�����
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
				//·�ɱ���һ����¼
				RIP Rp=new RIP();
				Rp.name=(char) ('A'+i-1);
				Rp.Net=Net;
				Rp.d=d;
				Rp.Next=next;
				Rp.time=new Date().getTime();//��ȡ��ǰʱ��
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
//��ӡ·�ɱ�
public static void PrintRips() {
	ArrayList<RIP> r;
	for(int i=1;i<=N;i++) {
		r=rips[i];
		System.out.printf("***********************·�ɱ�%c************************\n",(char)('A'+i-1));
		System.out.printf("��Ŀ������N\t\t����d\t\t��һ��·����X\n");
		for(int j=0;j<r.size();j++) {
			System.out.printf("%d\t\t\t%d\t\t%c\n", r.get(j).Net,r.get(j).d,r.get(j).Next);
		}
	}
}


//��ӡ���˽ṹ
public static void printStrucR() {

	//��ӡN--·��������
	System.out.println("The number of routers is "+N);
	//��ӡ·���ڽӱ�
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
//��ӡ��ѯ���
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
//����·�ɱ�
public static boolean update() throws ClassNotFoundException, IOException {
	//��ʾ���������޸ģ����ж��Ƿ�����
	boolean hasEdit=false;
	ArrayList<RIP> send;
	ArrayList<RIP> rip1[]=new ArrayList[N+1];
	for(int i=1;i<=N;i++) {
		rip1[i]= (ArrayList<RIP>) deepCopy(rips[i]);
	}
	int Ierror=-1;
	//�������·�ɴ�������
	if(error!='0') {
	Ierror=error-'A'+1;
	}
	//����ÿ��·�ɽ��
	for(int i=1;i<=N;i++) {
		//���·���������ִ�����·���� i+'A'-1���ھӽ�㴫�ͱ���
		if(i!=Ierror) {
		send=Send(rips[i],(char)(i+'A'-1));
		
		//������i��·�������ھӣ����µ�·�ɱ���send·�ɱ�Ƚ�
		for(int j=0;j<Routers[i].size();j++) {
			//��ȡ�ھ�·�������
			int index=(int)Routers[i].get(j);
			if(index!=Ierror) {
			//��·������������send���ع����ı��Ľ���һ��һ���Ĳ����ж�
			for(int x=0;x<send.size();x++) {
				int k;
				//��rip1����ɸ��Ƚ�
				for(k=0;k<rip1[index].size();k++) {
					//���ж��Ƿ����Ŀ�����磬����ڽ��бȽ�
					if(send.get(x).Net==rip1[index].get(k).Net) {
						//�ж���һ���Ƿ���ͬ������ͬ����update
						if(send.get(x).Next==rip1[index].get(k).Next) {
							//?ʱ������
							if(rip1[index].get(k).d!=send.get(x).d) {
							rip1[index].get(k).d=send.get(x).d;
							
							rip1[index].get(k).is_valid=true;
							hasEdit=true;
							}
							rip1[index].get(k).time=send.get(x).time;
						}
					
					//��һ����ͬ�����
					else {
						//���յ�����Ŀ�еľ���dС��·�ɱ�ľ�������·���ʲô������
						if(send.get(x).d<rip1[index].get(k).d) {
							rip1[index].get(k).d=send.get(x).d;
							rip1[index].get(k).time=send.get(x).time;
							rip1[index].get(k).Next=send.get(x).Next;
							rip1[index].get(k).is_valid=true;
							hasEdit=true;
						}
					}
					break;//����
				}
				}
				//��û���ҵ�Ŀ������N,��ô�ͽ�����Ŀ��ӵ�·�ɱ���
				//ʱ�䣿
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
	//����rips��д��
	for(int i=1;i<=N;i++) {
		rips[i]=(ArrayList<RIP>) deepCopy(rip1[i]);
		//����Ŀ����������
		MyCompare cmp=new MyCompare();
		Collections.sort(rips[i],cmp);
	}
	return hasEdit;
}
public static class MyCompare implements Comparator<RIP>{

	@Override
	public int compare(RIP x, RIP y) {
		// TODO �Զ����ɵķ������
		return x.Net-y.Net;
	}
	
}
//�仯·�ɱ� ��Ϊ�����һ����һ�ڵ���Ϊ���ͽ�㣬������Ӧ����ʱ��
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
				//180s �޸��� �����Ч
				rips[i].get(j).is_valid=false;
}
			else if(!rips[i].get(j).is_valid&&(t-rips[i].get(j).time)>120) {
				//�ٹ�120s�޸���ɾ��
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
        		
        //����1s
        while(true) {
        	//ÿ���1s ����·�ɱ�
        	try {
        		Thread.sleep(1000);
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
        	System.out.printf("\n------------------------��%d�θ���------------------------\n",++T);
        	if(update()) {
        		PrintRips();
        		n++;
        	}
        	else {
        		System.out.printf("����û���κ��м�ֵ���޸�!˵��ǰ��%d���޸ĺ�,ÿһ��·�������õ���ȫ��·����Ϣ!\n",n);
        		//�¼��ֶ����ƴ���
        		System.out.print("���ֶ������һ��·��������������,��û������������'0'��");
        		error=input.next().charAt(0);
        		
        		if(error!='0') {
        		System.out.printf("·����%c��������", error);
        		check1(error);
        		System.out.println("��ʾ����·���������·�ɱ�");
        		PrintRips();
        		}
        		
        		else {
        			System.out.printf("\n------------------------�Ѿ��޴�����------------------------\n");
        			break;
        		}
        		//break;
        	}
        	//check();
        }
        
	}*/

}
