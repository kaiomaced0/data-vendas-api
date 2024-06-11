package br.giraffus.model;

public enum TipoNotificacao {

    ESTOQUEBAIXO(1, "Estoque Baixo"),
    ALERTA(2, "Alerta"),
    SUCESSO(3, "Sucesso"),
    ERRO(4, "Erro"),
    LEMBRETE(5, "Lembrete"),
    MANUTENCAO(6, "Manutencao"),
    ADMIN(7, "Mensagem Admin"),
    FEEDBACK(8, "Feedback"),
    CONGRATULATIONS(9, "Congratulations"),
    PAGAMENTO(10, "Pagamento");



    private int id;
    private String label;

    TipoNotificacao(int id, String label){
        this.id = id;
        this.label = label;
    }

    public int getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static TipoNotificacao valueOf(Integer id) throws IllegalArgumentException {
        if (id == null)
            return null;
        for(TipoNotificacao t : TipoNotificacao.values()) {
            if (id.equals(t.getId()))
                return t;
        }
        throw new IllegalArgumentException("Id inválido:" + id);
    }
}
