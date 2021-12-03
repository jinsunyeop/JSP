package action;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import model.BoardDao;
import model.BoardDto;

public class UpdatePro implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		req.setCharacterEncoding("UTF-8");
		
		
		//새로운 파일을 등록하므로 WritePro.java와 같은 과정을 겪지만 Dao.updateArticle을 통하므로 db에선 update로 구현
		String savePath = "/upload";
		int uploadFileSizeLimit = 5 * 1024 * 1024;
		String encType ="UTF-8";
		ServletContext context = req.getServletContext(); 
		String Path = context.getRealPath(savePath); 
		

		
		try {
			MultipartRequest multi = new MultipartRequest(
					req,
					Path,
					uploadFileSizeLimit,
					encType,
					new DefaultFileRenamePolicy()
					);
			String UpdatefileName = multi.getFilesystemName("uploadFile"); //업로드된 파일이름
			//수정 전 파일 삭제를 하기 위한 과정 (외우자선엽아)
			String fileName = multi.getParameter("fname");
			String deleteFile = Path+"//"+fileName; 
			File file = new File(deleteFile); 
			//성공적으로 파일 삭제시 console창에 true
			System.out.println(file.delete());
			if(fileName==null) {
				System.out.println("파일 업로드 실패");
			}else {
				BoardDto article = new BoardDto();
				article.setNum(Integer.parseInt(multi.getParameter("num")));
				article.setId(multi.getParameter("id"));
				article.setPw(Integer.parseInt(multi.getParameter("pw")));
				article.setTitle(multi.getParameter("title"));
				article.setContent(multi.getParameter("content"));
				article.setFname(UpdatefileName);
				BoardDao dbpro = new BoardDao();
				dbpro.updateArticle(article);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
			
		
		
		
		return "/WEB-INF/library/updatePro.jsp";
	}

}
