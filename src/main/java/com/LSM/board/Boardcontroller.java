package com.LSM.board;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class Boardcontroller
 */
@WebServlet("/boardlist") //boardlist요청만 구현
public class Boardcontroller extends HttpServlet {
	private static final int PAGE_GROUP_SIZE= 10;
	//게시판 하단에 표시될 현재 글의 갯수로 만들어진 전체 페이지 수
	//[1] [2] [3] [4] [5]
	
	BoardDao boardDao = new BoardDao();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Boardcontroller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int page = 1;
		//게시판링크 접근할때 처음 페이지는 무조건 1페이지의 내용이 보여야 함
		//처음에 보여질 페이지의 번호의 초기값을 1로 초기화
		int totalBoardCount = boardDao.countBoard();//총 글 갯수
		if(request.getParameter("page") == null) {//참이면 링크를 타고 게시판으로 들어간 경우
			page = 1;
		} else { //유저가 보고싶은 페이지번호를 누른 경우
			page = Integer.parseInt( request.getParameter("page")); //유저가 클릭한 유저가 보고싶어하는 페이지의 번호
		}
		
		List<BoardDto> boardDtos = boardDao.boardList(page);
		//유저가 선택한 페이지에 해당하는 글10개 가져오기
		int totalPage = (int) Math.ceil((double)totalBoardCount / BoardDao.PAGE_SIZE);
		
		int startPage= (((page -1 )/PAGE_GROUP_SIZE)*PAGE_GROUP_SIZE) + 1 ; 
		int endPage = startPage + PAGE_GROUP_SIZE-1;
		
		//계산한 endPage값(startPage+9)이 실제 마지막 페이지값보다 작으면 마지막 페이지값으로 endPage값 대체, 
		//endPage가 3이고 실제 마지막 페이지는 10, endPage값이 더 작기때문에 마지막 페이지를 endPage로 대체
		if(endPage > totalPage ) {
			endPage = totalPage;
		} else {
			
		}
		
		request.setAttribute("boardDtos", boardDtos);//유저가 선택한 페이지에 해당하는 글
		request.setAttribute("currentPage", page);//유저가 현재 선택한 페이지 번호
		request.setAttribute("totalPage", totalPage); //전체 글 갯수로	계산한 전체 페이지
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		//총 글의 갯수로 표현될 전체 페이지의 수(37개 글이면 4 전달)
		
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("boardList.jsp");	
		
		//conPath자리에는 실제 실행시킬 jsp파일 이름, boardList.jsp에게 request객체를 전달해라 그후 boardList.jsp로 이동해라
		dispatcher.forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
