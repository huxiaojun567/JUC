package JUC;

@FunctionalInterface
interface Foo{
//    public void sayHello();
    public int add(int x,int y);
    public default int mul(int x, int y){
        return x * y;
    }
    public static  int dev(int x, int y){
        return x/y;
    }
}

/**
 * Lambda 表达式学习测试类
 *
 * 1. Lambda表达式的写法：(拷贝小括号， 写死右箭头， 落地大括号)：当接口中的方法只有一个时
 * 2. @FunctionalInterface，定义接口定义为函数式接口
 * 3. default : 在函数式接口中定义一个默认的方法
 * 4. static : 在函数式接口中定义一个静态的方法
 */
public class LambdaExpressDemo02 {

    public static void main(String[] args) {

//        Foo foo = () -> {
//            System.out.println("****hello Lambda Express****");
//        };
//        foo.sayHello();

        Foo foo = (int x,int y) -> {
            System.out.println( "开始+运算");
            return x + y;
        };
        int add = foo.add(5, 6);
        System.out.println(add);

        System.out.println(foo.mul(3,7));
        System.out.println(Foo.dev(6,3));

    }


}
