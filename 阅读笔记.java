/*
*java.io.Bits
*date:2016.5.23
*author:ameng
*reference link：http://www.xuebuyuan.com/2206784.html
*http://www.apihome.cn/api/java/FilterInputStream.html
http://www.fengfly.com/plus/view-214083-1.html
*/
Bit类包含的方法：（Byte向Int、Short、Float、Double、Boolean等数据类型之间的相互转换）
1、getBoolean  返回值：boolean     对应putBoolean
2、getChar  返回值：char         对应putChar
3、getInt  返回值：int           对应putInt
4、getLong 返回值：Long        对应putLong
5、getShort  返回值：Short     对应putShort
6、getFloat  返回值：Float     对应putFloat
7、getDouble 返回值：Double    对应putDouble
..........


/*
*java.io.BufferedInputStream
*date:2016.5.23
*author:ameng
*/
BufferedInputStream类继承FilterInputStream
其中包含的方法：
1、getInIfOpen()   判断输入流in是否打开
2、getBufIfOpen()    判断buffer输入流是否打开
3、BufferedInputStream(InputStream)  将输入流保存至buffer中
4、BufferedInputStream(InputStream in, int size) 将指定长度的流保存至buffer
5、fill()  填充更多的数据到buffer

（2016.5.28）已读列表：
1、java.io.Bits.java
2、BufferedInputStream
3、BufferedOutputStream
4、InputStream
5、ByteArrayInputStream
6、OutputStream
7、ByteArrayOutputStream
8、PipedInputStream
9、PipedOutputStream

(2016.5.30)阅读列表：
1、Reader
2、CharArrayReader
3、CharArrayWriter
4、CharConversionException
5、Closeable
6、Console
7、DataInput
8、DataInputStream
********************************************************************************
 //从输入流中读取两个字节的数据（short）
public final short readShort() throws IOException {
    int ch1 = in.read();    //in.read（）方法返回的输入流的下一个要读取的字节
    int ch2 = in.read();
    if ((ch1 | ch2) < 0)
        throw new EOFException();
    return (short)((ch1 << 8) + (ch2 << 0));
}
public final long readLong() throws IOException {
    readFully(readBuffer, 0, 8);
    return (((long)readBuffer[0] << 56) +
            ((long)(readBuffer[1] & 255) << 48) +
((long)(readBuffer[2] & 255) << 40) +
            ((long)(readBuffer[3] & 255) << 32) +
            ((long)(readBuffer[4] & 255) << 24) +
            ((readBuffer[5] & 255) << 16) +
            ((readBuffer[6] & 255) <<  8) +
            ((readBuffer[7] & 255) <<  0));
}
其中比较难懂的readUTF(),后边抽时间详细琢磨
********************************************************************************
9、DataOutputStream
10、DeleteOnExitHook   使用hashtable记录已经删除或者即将被删除的文件列表
11、EOFException       输入流读取到最后，没有可读的数据则会报此异常，继承IOException->Exception，打印出异常消息

(2016.6.1)阅读列表：
1、ExpiringCache         每查询几次就进行一次过期cache记录的删除
2、File  FilESystem      抽时间深层次剖析
3、FileDescriptor        文件描述符，在创建文件输入输出流时会被设置


//java.io中各个源码的作用
1、FilterInputStream
作用是用来“封装其它的输入流，并为它们提供额外的功能”。
它的常用的子类有BufferedInputStream和DataInputStream。
（1）BufferedInputStream的作用就是为“输入流提供缓冲功能，以及mark()和reset()功能”。
（2）DataInputStream 是用来装饰其它输入流，它“允许应用程序以与机器无关方式从底层输入流中读
取基本 Java 数据类型”。应用程序可以使用DataOutputStream(数据输出流)写入由DataInputStream(数据输入流)读取的数据。
2、FilterOutputStream
作用是用来“封装其它的输出流，并为它们提供额外的功能”。
它主要包括BufferedOutputStream, DataOutputStream和PrintStream。
(1) BufferedOutputStream的作用就是为“输出流提供缓冲功能”。
(2) DataOutputStream 是用来装饰其它输出流，将DataOutputStream和DataInputStream输入
流配合使用，“允许应用程序以与机器无关方式从底层输入流中读写基本Java 数据类型”。
(3) PrintStream 是用来装饰其它输出流。它能为其他输出流添加了功能，使它们能够方便地打印各种数据值表示形式。
3、BufferedInputStream 是缓冲输入流。它继承于FilterInputStream。
BufferedInputStream 的作用是为另一个输入流添加一些功能，例如，提供“缓冲功能”以及支持“mark()标记”和“reset()重置方法”。
BufferedInputStream 本质上是通过一个内部缓冲区数组实现的。例如，在新建某输入流对应的BufferedInputStream后，当我们通
过read()读取输入流的数据时，BufferedInputStream会将该输入流的数据分批的填入到缓冲区中。每当缓冲区中的数据被读完之后，
输入流会再次填充数据缓冲区；如此反复，直到我们读完输入流数据位置。
4、BufferedOutputStream 是缓冲输出流。它继承于FilterOutputStream。
BufferedOutputStream 的作用是为另一个输出流提供“缓冲功能”。
BufferedOutputStream通过字节数组来缓冲数据，当缓冲区满或者用户调用flush()函数时，它就会将缓冲区的数据写入到输出流中。
5、DataInputStream 是数据输入流。它继承于FilterInputStream。
DataInputStream 是用来装饰其它输入流，它“允许应用程序以与机器无关方式从底层输入流中读取基本Java 数据类型”。应用程序
可以使用DataOutputStream(数据输出流)写入由DataInputStream(数据输入流)读取的数据。
6、DataOutputStream 是数据输出流。它继承于FilterOutputStream。
DataOutputStream 是用来装饰其它输出流，将DataOutputStream和DataInputStream输入流配合使用，“允许应用程序以与机器无关
方式从底层输入流中读写基本Java 数据类型”。
7、PrintStream 是打印输出流，它继承于FilterOutputStream。
PrintStream 是用来装饰其它输出流。它能为其他输出流添加了功能，使它们能够方便地打印各种数据值表示形式。
与其他输出流不同，PrintStream 永远不会抛出 IOException；它产生的IOException会被自身的函数所捕获并设置错误标记，
用户可以通过 checkError() 返回错误标记，从而查看PrintStream内部是否产生了IOException。
另外，PrintStream 提供了自动flush 和 字符集设置功能。所谓自动flush，就是往PrintStream写入的数据会立刻调用flush()函数。
8、CharArrayReader 是字符数组输入流。它和ByteArrayInputStream类似，只不过ByteArrayInputStream是字节数组输入流，而
CharArray是字符数组输入流。CharArrayReader 是用于读取字符数组，它继承于Reader。操作的数据是以字符为单位！
9、CharArrayReader 用于写入数据符，它继承于Writer。操作的数据是以字符为单位！
10、PipedWriter 是字符管道输出流，它继承于Writer。
PipedReader 是字符管道输入流，它继承于Writer。
PipedWriter和PipedReader的作用是可以通过管道进行线程间的通讯。在使用管道通信时，必须将PipedWriter和PipedReader配套使用。
11、InputStreamReader和OutputStreamWriter 是字节流通向字符流的桥梁：它使用指定的 charset 读写字节并将其解码为字符。
InputStreamReader 的作用是将“字节输入流”转换成“字符输入流”。它继承于Reader。
OutputStreamWriter 的作用是将“字节输出流”转换成“字符输出流”。它继承于Writer。
12、FileReader 是用于读取字符流的类，它继承于InputStreamReader。要读取原始字节流，请考虑使用 FileInputStream。
FileWriter 是用于写入字符流的类，它继承于OutputStreamWriter。要写入原始字节流，请考虑使用 FileOutputStream。
13、BufferedReader 是缓冲字符输入流。它继承于Reader。
BufferedReader 的作用是为其他字符输入流添加一些缓冲功能。
要想读懂BufferReader的源码，就要先理解它的思想。BufferReader的作用是为其它Reader提供缓冲功能。创建BufferReader时，
我们会通过它的构造函数指定某个Reader为参数。BufferReader会将该Reader中的数据分批读取，每次读取一部分到缓冲中；操作完缓
冲中的这部分数据之后，再从Reader中读取下一部分的数据。
14、BufferedWriter 是缓冲字符输出流。它继承于Writer。
BufferedWriter 的作用是为其他字符输出流添加一些缓冲功能。
BufferedWriter通过字符数组来缓冲数据，当缓冲区满或者用户调用flush()函数时，它就会将缓冲区的数据写入到输出流中。
15、PrintWriter 是字符类型的打印输出流，它继承于Writer。
PrintStream 用于向文本输出流打印对象的格式化表示形式。它实现在 PrintStream 中的所有 print 方法。它不包含用于写入原始字节
的方法，对于这些字节，程序应该使用未编码的字节流进行写入。
16、FileInputStream 是文件输入流，它继承于InputStream。
通常，我们使用FileInputStream从某个文件中获得输入字节。
FileOutputStream 是文件输出流，它继承于OutputStream。
通常，我们使用FileOutputStream 将数据写入 File 或 FileDescriptor 的输出流。
17、实现序列化的2种方式Serializable和Externalizable的深入研究
序列化，就是为了保存对象的状态；而与之对应的反序列化，则可以把保存的对象状态再读出来。
简言之：序列化/反序列化，是Java提供一种专门用于的保存/恢复对象状态的机制。
一般在以下几种情况下，我们可能会用到序列化：
a）当你想把的内存中的对象状态保存到一个文件中或者数据库中时候；
b）当你想用套接字在网络上传送对象的时候；
c）当你想通过RMI传输对象的时候。
18、ObjectInputStream 和 ObjectOutputStream 的作用是，对基本数据和对象进行序列化操作支持。
创建“文件输出流”对应的ObjectOutputStream对象，该ObjectOutputStream对象能提供对“基本数据或对象”的持久存储；当我们需要读取
这些存储的“基本数据或对象”时，可以创建“文件输入流”对应的ObjectInputStream，进而读取出这些“基本数据或对象”。
注意： 只有支持 java.io.Serializable 或 java.io.Externalizable 接口的对象才能被ObjectInputStream/ObjectOutputStream所操作！
19、在java中，PipedOutputStream和PipedInputStream分别是管道输出流和管道输入流。
它们的作用是让多线程可以通过管道进行线程间的通讯。在使用管道通信时，必须将PipedOutputStream和PipedInputStream配套使用。
使用管道通信时，大致的流程是：我们在线程A中向PipedOutputStream中写入数据，这些数据会自动的发送到与PipedOutputStream对应的
PipedInputStream中，进而存储在PipedInputStream的缓冲中；此时，线程B通过读取PipedInputStream中的数据。就可以实现，线程A和线程B的通信。
20、ByteArrayOutputStream 是字节数组输出流。它继承于OutputStream。
ByteArrayOutputStream 中的数据被写入一个 byte 数组。缓冲区会随着数据的不断写入而自动增长。可使用 toByteArray() 和 toString() 获取数据。
ByteArrayInputStream 是字节数组输入流。它继承于InputStream。
它包含一个内部缓冲区，该缓冲区包含从流中读取的字节；通俗点说，它的内部缓冲区就是一个字节数组，而ByteArrayInputStream本质就是通过字节数组来实现的。
我们都知道，InputStream通过read()向外提供接口，供它们来读取字节数据；而ByteArrayInputStream 的内部额外的定义了一个计数器，它被用来跟踪 read() 方法要读取的下一个字节。
