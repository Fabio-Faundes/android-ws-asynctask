package br.unicamp.alunoservice;

import java.io.Serializable;

public class Aluno implements Cloneable, Serializable
{
    private int    ra;
    private String nome;
    private String emailAluno;
 
    public void setRa (int ra) throws Exception
    {
        if (ra < 0)
            throw new Exception ("Ra invalido");

        this.ra = ra;
    }   

    public void setNome (String nome) throws Exception
    {
        if (nome==null || nome.equals(""))
            throw new Exception ("Nome nao fornecido");

        this.nome = nome;
    }

    public void setEmailAluno (String emailAluno) throws Exception
    {
        if (emailAluno == null || emailAluno.trim().equals(""))
            throw new Exception ("Email Inválido!");

        this.emailAluno = emailAluno;
    }

    public int getRa ()
    {
        return this.ra;
    }

    public String getNome ()
    {
        return this.nome;
    }

    public String getEmailAluno ()
    {
        return this.emailAluno;
    }
    
    public Aluno ()
    {
        
    }

    public Aluno (int ra, String nome, String email) throws Exception
    {
        this.setRa(ra);
        this.setNome   (nome);
        this.setEmailAluno  (email);
    }

    @Override
    public String toString ()
    {
        String ret = "";
        ret += "RA: " + this.ra + " | ";
        ret += "Nome: " + this.nome + " | ";
        ret += "Email: " + this.emailAluno;

        return ret;
    }

    @Override
    public boolean equals (Object obj)
    {
        if (this==obj)
            return true;

        if (obj==null)
            return false;

        if (!(obj instanceof Aluno))
            return false;

        Aluno liv = (Aluno)obj;

        if (this.ra!=liv.ra)
            return false;

        if (this.nome.equals(liv.nome))
            return false;

        return this.emailAluno.equals(liv.emailAluno);
    }

    @Override
    public int hashCode ()
    {
        int ret=666;

        ret = 7*ret + new Integer(this.ra).hashCode();
        ret = 7*ret + this.nome.hashCode();
        ret = 7*ret + new Float(this.emailAluno).hashCode();

        return ret;
    }


    public Aluno (Aluno modelo) throws Exception
    {
        if(modelo == null)
            throw new Exception("Modelo para Aluno é nulo!");

        this.ra = modelo.ra;
        this.nome   = modelo.nome;
        this.emailAluno  = modelo.emailAluno;
    }
    
    public Object clone ()
    {
        Aluno ret=null;

        try
        {
            ret = new Aluno (this);
        }
        catch (Exception erro)
        {} // nao trato, pq this nunca eh null e construtor de
           // copia da excecao qdo seu parametro for null

        return ret;
    }
}