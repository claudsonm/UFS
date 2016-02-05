/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ed2;

/**
 *
 * @author aluno
 */
public interface FileOrganizer {
    public void addReg(Aluno a);
    public Aluno delReg(int matric);
    public Aluno getReg(int matric);
}
