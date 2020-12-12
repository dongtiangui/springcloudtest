package com.hik.consul.hikconsul;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import java.io.IOException;
public class Executor implements Watcher, Runnable{
    ZooKeeper zk;String zNode;
    public Executor(String hostPort, String zNode) throws IOException {
        this.zNode = zNode;
        zk = new ZooKeeper(hostPort, 3000, this);
    }
  public static void main(String[] args) {
    //      String hostPort = "localhost:2181";
    //      String zNode = "/dubbo/com.hk.services.UserServices";
    //      try {
    //          new Executor(hostPort, zNode).run();
    //      } catch (Exception e) {
    //          e.printStackTrace();
    //      }//
//    System.out.println(findUserById("1"));
  }
//    public static String findUserById(String ID) {
//        return new SQL(){{
//            SELECT("id,name");
//            SELECT("other");
//            FROM("user");
//            if(ID!=null){
//                WHERE("id = #{id}");
//            }
//            if(ID!=null){
//                WHERE("name = #{name}");
//            }
//        }}.toString(); //从这个toString可以看出，其内部使用高效的StringBuilder实现SQL拼接
//    }
    @Override
    public void run() {
        zk.getData(zNode, true, (rc, path, ctx, data, stat) -> {
            System.out.println("rc:" + rc);
            System.out.println("path:" + path);
            System.out.println("ctx:" + ctx);
            System.out.println("data:" + new String(data));
            System.out.println("stat:" + stat);
        },"传给服务端的内容,会在异步回调时传回来");
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void process(WatchedEvent event) {
        System.out.println("Receive watched event:" + event);
        if(event.getState() == Event.KeeperState.SyncConnected){
            System.out.println("zookeeper state is " + Event.KeeperState.SyncConnected);
        }
    }
}
