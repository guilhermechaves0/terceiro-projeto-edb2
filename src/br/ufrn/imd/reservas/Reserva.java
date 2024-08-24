package br.ufrn.imd.reservas;

public class Reserva {
  private int id;
  private String nome;
  private String voo;
  private String horario;

  public Reserva(int id, String nome, String voo, String horario) {
      this.id = id;
      this.nome = nome;
      this.voo = voo;
      this.horario = horario;
  }

  public int getId() {
      return id;
  }

  public String getNome() {
      return nome;
  }

  public String getVoo() {
      return voo;
  }

  public String getHorario() {
      return horario;
  }

  @Override
  public String toString() {
      return id + " (" + nome + ", " + voo + ", " + horario + ")";
  }
}
