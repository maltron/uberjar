import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

public class Main {
    
    public static void main(String[] args) throws Exception {
        ServletContextHandler context = new ServletContextHandler(
                ServletContextHandler.SESSIONS);
        context.setContextPath("/api");
        
        Server jetty = new Server(8080);
        jetty.setHandler(context);
        
        ServletHolder holder = context.addServlet(ServletContainer.class, "/*");
        holder.setInitOrder(0);
        
        holder.setInitParameter("jersey.config.server.provider.classnames",
                Resource.class.getCanonicalName());
        
        try {
            System.out.printf(">>> Start()\n"); jetty.start();
            System.out.printf(">>> Join()\n");  jetty.join();
            
        } finally {
            System.out.printf(">>> Destroy()\n"); jetty.destroy();
        }
        
   
    }

}
