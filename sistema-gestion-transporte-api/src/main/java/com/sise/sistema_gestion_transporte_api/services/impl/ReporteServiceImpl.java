package com.sise.sistema_gestion_transporte_api.services.impl;
import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Service;

import com.itextpdf.html2pdf.HtmlConverter;
import com.sise.sistema_gestion_transporte_api.payload.requests.ReporteCabeceraRequest;
import com.sise.sistema_gestion_transporte_api.payload.requests.ReporteMaestroRequest;
import com.sise.sistema_gestion_transporte_api.payload.requests.ReporteTablaIncidenciasRequest;
import com.sise.sistema_gestion_transporte_api.payload.requests.ReporteTablaRequest;
import com.sise.sistema_gestion_transporte_api.services.IReporteService;
import com.sise.sistema_gestion_transporte_api.shared.Constants;

@Service
public class ReporteServiceImpl implements IReporteService {

    @Override
    public byte[] reporteMaestro(ReporteMaestroRequest reporteMaestroRequest) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        HtmlConverter.convertToPdf(new StringBuilder()
                             .append("<!DOCTYPE html>")
                             .append("<html>")
                                .append("<head>")
                                    .append("<style>")
                                        .append(buildCSS())
                                    .append("</style>")
                                .append("</head>")
                                    .append("<body>")
                                 .append(buildFechaImpresion())
                                 .append(buildHeaderReporte())
                                 .append(buildTituloReporte(reporteMaestroRequest.getTituloReporte()))
                                 .append(buildCabeceraReporte(reporteMaestroRequest.getCabeceras()))
                                 .append(buildTablaReporte(reporteMaestroRequest.getTabla()))
                                 .append(buildTituloIncidenciaReporte(reporteMaestroRequest.getTituloIncidenciasReporte()))
                                 .append(buildTablaIncidenciaReporte(reporteMaestroRequest.getTablaIncidencias()))
                                       .append("</body>")
                             .append("</html>")
                             .toString(),

        byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    private String buildFechaImpresion(){
        LocalDateTime fechaHoraActual = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return "<p>Fecha impresión: "+fechaHoraActual.format(formato)+"</p>";
    }

    private String buildHeaderReporte(){
        return new StringBuilder()
            .append("<div class=\"header-pdf\">")
            
                .append("<img src=\"data:image/png;base64, "+ Constants.LOGO_B64+"\"></img>")
                    .append("<div class=\"titulos\">")
                              .append("<h1> 'MOVILPERU' </h1>")
                              .append("<h3> Direccion : Av. César Vallejo 603 - Lince-Lima-Peru </h3>")                          
                .append("</div>")
            .append("</div>")
            .toString();
    }

    private String buildTituloReporte(String titulo){
        return new StringBuilder()
            .append("<h2 class=\"titulo-reporte\">")
            .append(titulo)
            .append("</h2>")

        .toString();
    }

        private String buildCabeceraReporte(List<ReporteCabeceraRequest> reporteCabeceraRequest){
        if (reporteCabeceraRequest == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("<table class=\"cabeceras-reporte\"><tbody>");
        sb.append("<tr>");

        for (int i = 0; i < reporteCabeceraRequest.size(); i++) {
            if (i%2==0) {

            sb.append("</tr>")
                .append("<tr>");
            }

            sb.append("<td>")
                .append("<p>")
                    .append("<b>")
                     .append(reporteCabeceraRequest.get(i).getCampo()+": ")
                  .append("</b>")
            .append(reporteCabeceraRequest.get(i).getContenido())
            .append("</p>")
            .append("</td>");
        }
        sb.append("</tr>");
        sb.append("</tbody></table>");

        return sb.toString();
    }

    private String buildTablaReporte(ReporteTablaRequest reporteTablaRequest){
        if (reporteTablaRequest == null) {
            return "<p>No hay datos</p>";
        }

        StringBuilder sb = new StringBuilder();
        sb
        .append("<table class=\"tabla-data\">")
        .append("<thead><tr>");

        if (reporteTablaRequest.getCabeceras() != null) {
            for (String cabecera : reporteTablaRequest.getCabeceras()) {
                sb
                .append("<th>")
                    .append(cabecera)
                .append("</th>");
            }
        }

        sb
        .append("</tr></thead>")
        .append("<tbody>");

        if(reporteTablaRequest.getData() != null){
            for (List<String> datas : reporteTablaRequest.getData()) {
                sb.append("<tr>");
                for (String data : datas) {
                    sb
                    .append("<td>")
                        .append(data)
                    .append("</td>");
                }
                sb.append("</tr>");
            }
        }

        sb
        .append("</tbody>")
        .append("</table>");
        return sb.toString();
    }

    private String buildTituloIncidenciaReporte(String tituloIncidencia){
        return new StringBuilder()
            .append("<h2 class=\"titulo2-reporte2\">")
            .append(tituloIncidencia)
            .append("</h2>")

        .toString();
    }

    private String buildTablaIncidenciaReporte(ReporteTablaIncidenciasRequest reporteTablaIncidenciasRequest){
        if (reporteTablaIncidenciasRequest == null) {
            return "<p>No hay datos</p>";
        }

        StringBuilder sb = new StringBuilder();
        sb
        .append("<table class=\"tabla-data\">")
        .append("<thead><tr>");

        if (reporteTablaIncidenciasRequest.getCabeceras() != null) {
            for (String cabecera : reporteTablaIncidenciasRequest.getCabeceras()) {
                sb
                .append("<th>")
                    .append(cabecera)
                .append("</th>");
            }
        }

        sb
        .append("</tr></thead>")
        .append("<tbody>");

        if (reporteTablaIncidenciasRequest.getData() != null) {
            for (List<String> datas : reporteTablaIncidenciasRequest.getData()) {
                sb.append("<tr>");
                for (String data : datas) {
                    sb
                    .append("<td>")
                        .append(data)
                    .append("</td>");
                }
                sb.append("</tr>");
            }
        }

        sb
        .append("</tbody>")
        .append("</table>");
        return sb.toString();
    }

    private String buildCSS(){
        return new StringBuilder()
        .append("@page {")
            .append("size: A4;")
                .append("@bottom-right: {")
                    .append("content: \"Página \" counter(page) \" de \" counter(pages);")
                .append("}")  
        .append("}")
       .append("body{")  
               .append("font-family: helvetica;")              
        .append("}")

        .append(".header-pdf {")
                .append("background-color: "+Constants.PRIMARY_COLOR_REPORT+";")
                .append("border-radius: 10px;")
                .append("padding: 20px;")
                .append("display: flex;")
                .append("flex-direction: row-reverse;")
                .append("justify-content: space-between;")  
                .append("align-items: center;")    
         .append("}")

        .append(".header-pdf h1 {")
            .append("padding-top: 0px;")
            .append("color: #8B8000;")  
            .append("font-family: sans-serif;")  
            .append("text-align: center;")
            .append("margin: 0px;")
            .append("magin-top: 6px;")
            .append("font-size: 60px;")  
        .append("}")
        
        .append(".header-pdf h3 {")
            .append("color: black;")  
            .append("text-align: justify;")
            .append("margin: 0px;")
            .append("font-weight: normal;")
        .append("}")

        .append(".header-pdf .titulos {")
            .append("width:100%;")
        .append("}")

        .append(".header-pdf img {")
            .append("width: 150px;")
            .append("height: 130px;")
            .append("text-align: center;")
            .append("border-radius: 10px;")
        .append("}")
        
        .append("h2.titulo-reporte{")
            .append("background-color: "+Constants.PRIMARY_COLOR_REPORT+";")
            .append("border: 5px  #8B8000 solid;")
            .append("border-radius: 10px;")
            .append("text-align: center;")
            .append("color: black;")
            .append("padding: 10px;")
         .append("}")
        

         .append("h2.titulo2-reporte2{")
            .append("background-color: "+Constants.PRIMARY_COLOR_REPORT+";")
            .append("border: 5px  #8B8000 solid;")
            .append("border-radius: 10px;")
            .append("text-align: center;")
            .append("color: black;")
            .append("padding: 10px;")
         .append("}")

        .append(".cabeceras-reporte {")
            .append("width: 100%;")
            .append("border-collapse: separate;")
            .append("border-spacing: 8;")
            .append("border-radius: 10px;")
        .append("}")

        .append(".cabeceras-reporte p {")
           .append("margin: 0px;")
           .append("margin-top: 5px;")
        .append("}")

        .append(".tabla-data{")
            .append("padding: 5px;")
            .append("width: 100%;")
            .append("margin-top: 20px;")
        .append("}")

        .append(".tabla-data td, .tabla-data th {")
            .append("border: 1px #c4c4c4 solid;")
        .append("}")

        .append(".tabla-data thead tr {")
            .append("border: 2px black solid;")
            .append("background-color: "+Constants.PRIMARY_COLOR_REPORT+";")
            .append("font-weight: bold;")
        .append("}")

        .append(".tabla2-data2{")
            .append("padding: 5px;")
            .append("width: 100%;")//tamaño de la tabla 
            .append("margin-top: 20px;")
        .append("}")

        .toString();        
    }
   
}
