package action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import model.BoardDao;
import model.BoardDto;

public class WritePro implements CommandAction{	
	@Override
	public String requestPro(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		req.setCharacterEncoding("UTF-8");
		
		//파일 업로드를 위한 설정
		int uploadFileSizeLimit = 5 * 1024 * 1024;
		String encType ="UTF-8";
		
		//실제 업로드 경로 
		String savePath = "/upload";
		ServletContext context = req.getServletContext(); 
		String uploadFilePath = context.getRealPath(savePath); 
		System.out.println(context);
		try {
			MultipartRequest multi = new MultipartRequest(
					req,
					uploadFilePath,
					uploadFileSizeLimit,
					encType,
					new DefaultFileRenamePolicy()
					);
			String fileName = multi.getFilesystemName("uploadFile"); //업로드된 파일이름
			if(fileName==null) {
				System.out.println("파일 업로드 실패");
			}else {				
				BoardDto article = new BoardDto();
				article.setId(multi.getParameter("id"));
				article.setPw(Integer.parseInt(multi.getParameter("pw")));
				article.setTitle(multi.getParameter("title"));
				article.setContent(multi.getParameter("content"));
				article.setFname(fileName);
				BoardDao dbpro = new BoardDao();
				dbpro.insertArticle(article); //
			}
		}catch(Exception e) {
			e.printStackTrace();
		}		
		return "/WEB-INF/library/writePro.jsp";
	}

}
