package action;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BoardDao;

public class DeleteProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		req.setCharacterEncoding("UTF-8");
		int num = Integer.parseInt(req.getParameter("num"));
		
		BoardDao dbPro = new BoardDao();
		dbPro.deleteArticle(num);
		
		String savePath = "/upload"; //업로드 폴더이름
		ServletContext context = req.getServletContext(); 
		String path = context.getRealPath(savePath);  //업로드 폴더 절대 경로
		String fileName = req.getParameter("fname");
		String deleteFile = path+"//"+fileName;
		
		File file = new File(deleteFile);
		System.out.println(file);
		System.out.println(file.delete());
		
		return "/WEB-INF/library/deletePro.jsp";
	}

}
