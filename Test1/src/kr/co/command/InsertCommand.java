package kr.co.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.dao.MemberDAO;
import kr.co.domain.CommandAction;
import kr.co.domain.MemberDTO;

public class InsertCommand implements Command{

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw1");
		String name = request.getParameter("name");
		String nickname = request.getParameter("nickname");
		String tell1 = request.getParameter("tell");
		String address = request.getParameter("address");
		int tell = Integer.parseInt(tell1);
		
		MemberDTO dto = new MemberDTO(-1, id, pw, name, nickname, tell, address, null);
		new MemberDAO().insert(dto);
		
	
		return new CommandAction(true, "insertcomplete.jsp");
	}

}
