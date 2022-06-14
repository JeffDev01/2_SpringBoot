package test;

import dao.ProdutoDAO;
import model.Produto;

public class TestDataBasesActions {

    public static void main(String[] args) {

        Produto produto = new Produto("Celular LG", "Com OLED");

        ProdutoDAO produtoDAO = new ProdutoDAO();
        produtoDAO.inserir(produto);



    }
}
