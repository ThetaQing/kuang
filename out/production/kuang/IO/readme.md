# IO流
## 1、 基本概念
流：内存与存储设备之间传输数据的通道。   

输入流：将<存储设备>的内容读入到<内存>(程序)中。  
输出流：将<内存>(程序)中的内容写入到<存储设备中>。  
流的分类：  
按单位：  
* 字节流：以字节为单位，可以读写所有数据；  
* 字符流：以字符为单位，只能读写文本数据。  

按功能：  
* 节点流：具有实际传输数据的读写功能；  
* 过滤流：在节点流的基础上增强功能。  
## 2、字节流
字节流抽象类  
InputStream：表示输入字节流的所有类的超类； 
*  abstract int read() 
   从输入流读取数据的下一个字节。  
*  int read(byte[] b) 
   从输入流读取一些字节数，并将它们存储到缓冲区 b 。  
*  int read(byte[] b, int off, int len) 
   从输入流读取最多 len字节的数据到一个字节数组。  
* void close() 
  关闭此输入流并释放与流相关联的任何系统资源。  

OutputStream：表示字节输出流的所有类的超类。  
* void write(byte[] b)
  将 b.length字节从指定的字节数组写入此输出流。  
* void write(byte[] b, int off, int len) 
  从指定的字节数组写入 len个字节，从偏移 off开始输出到此输出流。  
* abstract void write(int b) 
  将指定的字节写入此输出流。  
* void close() 
   关闭此输出流并释放与此流相关联的任何系统资源。  
* void flush() 
   刷新此输出流并强制任何缓冲的输出字节被写出。  

### 2.1 文件字节流
FileInputStream 从文件系统中的某个文件中获得输入字节  
* int read() 
  从该输入流读取一个字节的数据。  
* int read(byte[] b) 
  从该输入流读取最多 b.length个字节的数据为字节数组。  
* int read(byte[] b, int off, int len) 
  从该输入流读取最多 len字节的数据为字节数组。  


FileOutputStream  
* void write(byte[] b) 
  将 b.length个字节从指定的字节数组写入此文件输出流。  
* void write(byte[] b, int off, int len) 
  将 len字节从位于偏移量 off的指定字节数组写入此文件输出流。  
* void write(int b) 
  将指定的字节写入此文件输出流。  
### 2.2 字节缓冲流
缓冲流：提高IO效率，减少访问磁盘的次数；数据存储在缓冲区中，flush是将缓存区的
内容写入文件中，也可以直接close.
BufferedInputStream  
* int read() 
  见 read法 InputStream的一般合同。  
* int read(byte[] b, int off, int len) 
  从给定的偏移开始，将字节输入流中的字节读入指定的字节数组。 

BufferedOutputStream
* void flush() 
  刷新缓冲输出流。  
* void write(byte[] b, int off, int len) 
  从指定的字节数组写入 len个字节，从偏移 off开始到缓冲的输出流。  
* void write(int b) 
  将指定的字节写入缓冲的输出流。  

### 2.3 对象流
增加了缓冲区功能；增加了读写8种基本数据类型和字符串的功能；增加了读写对象的功能。  
使用流传输对象的过程称为序列化（把内存中的对象写入到存储设备，即文件中）、反序列化（从文件中读取一个对象到内存中）。  
注意事项：  
* ==不是所有的对象都能序列化，必须将对象的类实现`Serializable`接口标识这个类可以序列化，才能序列化该类的对象。==  
* 序列化类中的对象属性（如果有的话）也要求实现`Serializable`接口。  
* 使用transient（瞬时的）修饰属性，那么这个属性就不能序列化（也就是该属性的值不写入到文件，反序列化之后该属性的值是默认值）。  
* 静态属性不能被序列化(序列化保存的是对象的状态，静态变量表示类的状态，因此序列化不保存静态变量)，只写一个对象测试成功的原因是因为在同一个进程中，jvm已经把'country'
加载进来了，所以获取的是加载的‘country’，但如果写多个或者重新写程序读入文件，此时'country'就是初始时的信息；  
* 序列化多个对象
ObjectInputStream（反序列化）  
* int read() 
  读取一个字节的数据。  
*  int read(byte[] buf, int off, int len) 
  读入一个字节数组。  
* Object readObject() 
  从ObjectInputStream读取一个对象。  

ObjectOutputStream（序列化）  
* void writeObject(Object obj) 
  将指定的对象写入ObjectOutputStream。  

## 3 字符流
Reader:字符输入流,用于读取字符流的抽象类；  
* int read() 
  读一个字符  
* int read(char[] cbuf) 
  将字符读入数组。  
* int read(CharBuffer target) 
尝试将字符读入指定的字符缓冲区。  
* boolean ready() 
告诉这个流是否准备好被读取。 
* abstract int read(char[] cbuf, int off, int len) 
  将字符读入数组的一部分。   

Writer：字符输出流,用于写入字符流的抽象类；  
* void write(char[] cbuf) 
  写入一个字符数组。  
* abstract void write(char[] cbuf, int off, int len) 
  写入字符数组的一部分。  
* void write(int c) 
  写一个字符  
* void write(String str) 
  写一个字符串  
* void write(String str, int off, int len) 
  写一个字符串的一部分。  

### 3.1 常见字符编码
ISO-8859-1收录除ASCII外，还包括西欧、希腊语、泰语、阿拉伯语、希伯来语对应的文字符号。  
UTF-8 针对Unicode码表的可变成度字符编码；  
GB2312 简体中文；  
GBK 简体中文、扩充；  
BIG5台湾，繁体中文。  
### 3.2 文件字符流  
FileReader 用于读取字符流  
* public int read(char[] cbuf, int offset,
                  int length)
           throws IOException将字符读入数组的一部分。 

* public int read()
           throws IOException读一个字符 
  重写： 
  read在类 Reader 
  结果 
  字符读取，如果已经达到流的结尾，则为-1.  
  
FileWriter用于写入字符文件  
* public void write(int c)
             throws IOException写一个字符 
  重写： 
  write在类别 Writer 
  参数 
  c - int指定要写入的字符 
* public void write(char[] cbuf,
                    int off,
                    int len)
             throws IOException写入字符数组的一部分。 
* public void write(String str,
                    int off,
                    int len)
             throws IOException写一个字符串的一部分。 
  重写： 
  write在类别 Writer 
### 3.3 字符缓冲流
BufferedReader从字符输入流读取文本，缓冲字符，以提供字符，数组和行的高效读取。  
* int read() 
  读一个字符  
* int read(char[] cbuf, int off, int len) 
  将字符读入数组的一部分。  
*  String readLine() 
  读一行文字。  

BufferedWriter将文本写入字符输出流，缓冲字符，以提供单个字符，数组和字符串的高效写入。   
* void newLine() 
  写一行行分隔符。  
*  void write(char[] cbuf, int off, int len) 
  写入字符数组的一部分。  
*  void write(int c) 
  写一个字符  
* void write(String s, int off, int len) 
  写一个字符串的一部分。  

### 3.4 打印流
PrintWriter将对象的格式表示打印到文本输出流。  
原样打印到目标中。  

## 4 转换流
桥转换流：InputStreamReader、OutputStreamWriter  
* 可将字节流转换为字符流；  
* 可设置字符的编码方式。  


InputStreamReader从字节流到字符流的桥：它读取字节，并使用指定的charset将其解码为字符 。
* int read() 
读一个字符  
* int read(char[] cbuf, int offset, int length) 
将字符读入数组的一部分。  


OutputStreamWriter字符的桥梁流以字节流：向其写入的字符编码成使用指定的字节charset 。  
* void write(char[] cbuf, int off, int len) 
  写入字符数组的一部分。  
* void write(int c) 
  写一个字符  
* void write(String str, int off, int len) 
  写一个字符串的一部分。  
## 5 File 类
public class File
extends Object
implements Serializable, Comparable<File>文件和目录路径名的抽象表示。   
* boolean createNewFile() 
  当且仅当具有该名称的文件尚不存在时，原子地创建一个由该抽象路径名命名的新的空文件。  
* boolean delete() 
  删除由此抽象路径名表示的文件或目录。  
*  boolean exists() 
  测试此抽象路径名表示的文件或目录是否存在。 
*  String getAbsolutePath() 
  返回此抽象路径名的绝对路径名字符串。  
*  String getName() 
  返回由此抽象路径名表示的文件或目录的名称。  
*  String getParent() 
  返回此抽象路径名的父 null的路径名字符串，如果此路径名未命名为父目录，则返回null。  
*  boolean isDirectory() 
  测试此抽象路径名表示的文件是否为目录。  
*  boolean isFile() 
  测试此抽象路径名表示的文件是否为普通文件。  
* long length() 
返回由此抽象路径名表示的文件的长度。  
* File[] listFiles(FileFilter filter) 
返回一个抽象路径名数组，表示由此抽象路径名表示的满足指定过滤器的目录中的文件和目录。  
* boolean renameTo(File dest) 
重命名由此抽象路径名表示的文件。  
* boolean mkdir() 
  创建由此抽象路径名命名的目录。  
 
 ### 5.1 FileFilter 
 FileFilter文件过滤  
* boolean accept(File pathname)测试指定的抽象路径名是否应包含在路径名列表中。 
  参数 
  pathname - 要测试的抽象路径名 
  结果 
  true if and only if pathname should be included 

## 6 Properties类
表示一组持久的属性。Properties可以保存到流中或从流中加载。 属性列表中的每个键及其对应的值都是一个字符串。   
与流相关的方法：  
* void list(PrintStream out) 
  将此属性列表打印到指定的输出流。  
*  void list(PrintWriter out) 
  将此属性列表打印到指定的输出流。  
*  void load(InputStream inStream) 
  从输入字节流读取属性列表（键和元素对）。  
*  void load(Reader reader) 
  以简单的线性格式从输入字符流读取属性列表（关键字和元素对）。  
*  void store(OutputStream out, String comments) 
  将此属性列表（键和元素对）写入此 Properties表中，以适合于使用 load(InputStream)方法加载到 Properties表中的格式输出流。  
*  void store(Writer writer, String comments) 
  将此属性列表（键和元素对）写入此 Properties表中，以适合使用 load(Reader)方法的格式输出到输出字符流。  


