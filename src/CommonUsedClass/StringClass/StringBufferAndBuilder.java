package CommonUsedClass.StringClass;

public class StringBufferAndBuilder {
    public static void main(String[] args) {
        StringBuffer stringBuffer = new StringBuffer();
        // 追加
        stringBuffer.append("hello");
        System.out.println(stringBuffer.toString());

        stringBuffer.append("world");
        System.out.println(stringBuffer.toString());

        // 插入
        stringBuffer.insert(0,"Song");
        System.out.println(stringBuffer.toString());

        // replace

        System.out.println(stringBuffer.replace(0, 4, "who?"));

        // delete
        System.out.println(stringBuffer.delete(2, 5));

        //timeCompare();

        double r = (1.4-0.5)/0.9;
        System.out.println(r);


    }
    public static void timeCompare(){

        // String
        long start = System.currentTimeMillis();
        String string = "";
        for (int i = 0; i < 99999; i++) {
            string+=i;
        }
        long end = System.currentTimeMillis();
        System.out.println("String time: "+(end - start));

        // stringBuffer
        start = System.currentTimeMillis();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < 99999; i++) {
            stringBuffer.append(i);
        }
        end = System.currentTimeMillis();
        System.out.println("stringBuffer time: "+(end - start));
    }


}
