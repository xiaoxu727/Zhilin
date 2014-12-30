package Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BLL.HouseBLL;

/**
 * Servlet implementation class Test
 */
@WebServlet("/Test")
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Test() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("test");
		String result = "[{\"cartodb_id\":11,\"name\":\"this is a string  \",\"description\":null,\"created_at\":null,\"updated_at\":null,\"lat\":null,\"lon\":null,\"agent\":null,\"address\":null,\"contact_number\":null,\"the_geom\":{\"class\":\"class org.postgresql.util.PGobject\",\"type\":\"geometry\",\"value\":\"0101000020E61000000000000000805BC00000000000804540\"}},{\"cartodb_id\":1,\"name\":\"this is a string    \",\"description\":null,\"created_at\":null,\"updated_at\":null,\"lon\":-110,\"lat\":43,\"agent\":\"xu                  \",\"address\":\"Shanghai ECNU                                                                                       \",\"contact_number\":null,\"the_geom\":{\"class\":\"class org.postgresql.util.PGobject\",\"type\":\"geometry\",\"value\":\"0101000020E61000000000000000805BC00000000000804540\"}}]";
//		HouseBLL bll = new HouseBLL();
//		String result = bll.getAllHouses();
		System.out.println(request.getAttribute("tableName"));
		PrintWriter out = response.getWriter();
		out.println(result);
		out.flush();
	
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HouseBLL bll = new HouseBLL();
		String result = bll.getAllHouses();
		PrintWriter out = resp.getWriter();
		out.println(result);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		double lat = (double) req.getAttribute("lat");
		double lon = (double) req.getAttribute("lon");
		String name = (String) req.getAttribute("Name");
		String agent = (String) req.getAttribute("agent");
		String address = (String) req.getAttribute("address");
		String tel = (String) req.getAttribute("tel");
		HouseBLL bll = new HouseBLL();
		bll.addNewHouse(agent, name, address, tel, lat, lon);

	}

}
