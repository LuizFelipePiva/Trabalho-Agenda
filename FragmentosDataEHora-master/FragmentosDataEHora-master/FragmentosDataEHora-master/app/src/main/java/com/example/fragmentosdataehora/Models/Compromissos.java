package com.example.fragmentosdataehora.Models;

public class Compromissos {
    private int id;

    private int hora;
    private int minuto;
    private int dia;

    private int mes;
    private int ano;

    private String descricao;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public int getMinuto() {
        return minuto;
    }

    public void setMinuto(int minuto) {
        this.minuto = minuto;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public String getHoraFormatada() {
        return String.format("%02d:%02d", hora, minuto);
    }

    public String getDataFormatada() {
        return dia + "/" + mes + "/" + ano;
    }

    @Override
    public String toString(){
        return "id:" + id + " Data:" + getDataFormatada() + " Hora:" + getHoraFormatada() + " Descrição:" + descricao;
    }
}