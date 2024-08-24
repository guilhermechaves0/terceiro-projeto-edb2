package br.ufrn.imd.reservas;

public class ArvoreAVL {
  private class No {
      Reserva reserva;
      No esquerda, direita;
      int altura;

      public No(Reserva reserva) {
          this.reserva = reserva;
          this.altura = 1;
      }
  }

  private No raiz;

  public void inserirReserva(Reserva reserva) {
      raiz = inserir(raiz, reserva);
  }

  private No inserir(No No, Reserva reserva) {
      if (No == null) return new No(reserva);

      if (reserva.getId() < No.reserva.getId()) {
          No.esquerda = inserir(No.esquerda, reserva);
      } else if (reserva.getId() > No.reserva.getId()) {
          No.direita = inserir(No.direita, reserva);
      } else {
          return No;
      }

      No.altura = 1 + Math.max(altura(No.esquerda), altura(No.direita));

      return balancear(No);
  }

  public void removerReserva(int id) {
      raiz = remover(raiz, id);
  }

  private No remover(No No, int id) {
      if (No == null) return No;

      if (id < No.reserva.getId()) {
          No.esquerda = remover(No.esquerda, id);
      } else if (id > No.reserva.getId()) {
          No.direita = remover(No.direita, id);
      } else {
          if ((No.esquerda == null) || (No.direita == null)) {
              No temp = null;
              if (temp == No.esquerda) temp = No.direita;
              else temp = No.esquerda;

              if (temp == null) {
                  temp = No;
                  No = null;
              } else No = temp;
          } else {
              No temp = minValueNo(No.direita);
              No.reserva = temp.reserva;
              No.direita = remover(No.direita, temp.reserva.getId());
          }
      }

      if (No == null) return No;

      No.altura = Math.max(altura(No.esquerda), altura(No.direita)) + 1;

      return balancear(No);
  }

  private No minValueNo(No No) {
      No atual = No;
      while (atual.esquerda != null)
          atual = atual.esquerda;

      return atual;
  }

  private int altura(No No) {
      return No == null ? 0 : No.altura;
  }

  private No balancear(No No) {
      int balanceamento = getBalanceamento(No);

      if (balanceamento > 1 && getBalanceamento(No.esquerda) >= 0)
          return rotacaoDireita(No);

      if (balanceamento > 1 && getBalanceamento(No.esquerda) < 0) {
          No.esquerda = rotacaoEsquerda(No.esquerda);
          return rotacaoDireita(No);
      }

      if (balanceamento < -1 && getBalanceamento(No.direita) <= 0)
          return rotacaoEsquerda(No);

      if (balanceamento < -1 && getBalanceamento(No.direita) > 0) {
          No.direita = rotacaoDireita(No.direita);
          return rotacaoEsquerda(No);
      }

      return No;
  }

  private int getBalanceamento(No No) {
      return No == null ? 0 : altura(No.esquerda) - altura(No.direita);
  }

  private No rotacaoDireita(No y) {
      No x = y.esquerda;
      No T2 = x.direita;

      x.direita = y;
      y.esquerda = T2;

      y.altura = Math.max(altura(y.esquerda), altura(y.direita)) + 1;
      x.altura = Math.max(altura(x.esquerda), altura(x.direita)) + 1;

      return x;
  }

  private No rotacaoEsquerda(No x) {
      No y = x.direita;
      No T2 = y.esquerda;

      y.esquerda = x;
      x.direita = T2;

      x.altura = Math.max(altura(x.esquerda), altura(x.direita)) + 1;
      y.altura = Math.max(altura(y.esquerda), altura(y.direita)) + 1;

      return y;
  }

  public Reserva buscarReserva(int id) {
      return buscar(raiz, id);
  }

  private Reserva buscar(No No, int id) {
      if (No == null || No.reserva.getId() == id)
          return No != null ? No.reserva : null;

      if (No.reserva.getId() > id)
          return buscar(No.esquerda, id);

      return buscar(No.direita, id);
  }

  public void imprimirEmPreOrdem() {
      preOrdem(raiz);
  }

  private void preOrdem(No No) {
      if (No != null) {
          System.out.println(No.reserva);
          preOrdem(No.esquerda);
          preOrdem(No.direita);
      }
  }
}
