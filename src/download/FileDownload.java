package download;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/download.do") //서블릿으로 매핑요청해도 되는데 어차피 바꿀일이 없으니 어노테이션 사용
public class FileDownload extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doHandle(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doHandle(req, resp);
	}
	
	// 나도 이해하려고 노력중
	public void doHandle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String savePath = "/upload"; //업로드 폴더이름
		ServletContext context = req.getServletContext(); 
		String path = context.getRealPath(savePath);  //업로드 폴더 절대 경로
		
		String fileName = req.getParameter("fileName");
		
		String downFile = path+"//"+fileName;
		File f = new File(downFile);
		String encodeFile = "attachment; filename*=" + "UTF-8"+"''"+URLEncoder.encode(fileName,"UTF-8");
		
		resp.setContentType("application/octet-steam; utf-8");
		resp.setHeader("Content-Disposition", encodeFile);
		
		FileInputStream in = new FileInputStream(f);
		OutputStream out = resp.getOutputStream();
		byte [] buffer = new byte[1024*5];
		while(true) {
			int count = in.read(buffer);
			if(count==-1)
				break;
			out.write(buffer,0,count);
		}
		in.close();
		out.close();
	}



	
	
}
