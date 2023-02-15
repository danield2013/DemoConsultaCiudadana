package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class UploadGuiaTramite
 */
@WebServlet("/UploadGuiaTramite")
public class UploadGuiaTramite extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadGuiaTramite() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		/*
		Configuracion configuracion = new Configuracion();
		String url_documentos = configuracion.getUrl_sigpro() + configuracion.getUrl_carpeta().replace("nombrecarpeta", "documentos")
				+ configuracion.getUrl_subcarpeta().replace("nombresubcarpeta", "uploaded");
		*/
		// Tamaño máximo permito para la subida de archivos -> 10485760 - 10MB
		MultipartRequest mpr = new MultipartRequest(request, "", 10485760);

		String codigoCatastral = mpr.getParameter("nombre");
		String tipoDocumento = mpr.getParameter("descripcion");
		String archivos = mpr.getParameter("archivos");
		String url = mpr.getParameter("redirect");
		
		boolean guardado = false;
		/*
		if (codigoCatastral != null && archivos != null) {
			MetodosDocumento metodos = new MetodosDocumento();
			guardado = metodos.guardarDocumentoPredio(codigoCatastral, tipoDocumento, archivos, url_documentos);
		}
		*/
		response.sendRedirect(url + "?codigoCatastral=" + codigoCatastral + "&tipdo_codigo=" + tipoDocumento + "&guardado=" + guardado);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
