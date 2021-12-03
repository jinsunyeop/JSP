package action;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BoardDao;
import model.BoardDto;

public class DeleteFormAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		int num = Integer.parseInt(req.getParameter("num"));
		BoardDao dbPro = new BoardDao();
		BoardDto article = dbPro.updateGetArticle(num);
		
		req.setAttribute("article", article);
		return "/WEB-INF/library/deleteForm.jsp";
	}

}
