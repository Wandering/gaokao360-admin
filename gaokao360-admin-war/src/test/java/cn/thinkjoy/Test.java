package cn.thinkjoy;

/**
 * Created by Michael on 2015/12/1.
 */
public class Test {
    public static void main(String[] args){
        for(int i=0;i<66;i++) {
            System.out.println("INSERT INTO  gaokao360_role_resource\n" +
                    "(creator,createDate,lastModifier,lastModDate,status,resourceActionId,resourceId,roleId)\n" +
                    "VALUES\n" +
                    "(1,1439966937380,1,1439966937380,10,"+i+",1,1)");
        }
    }
}
