/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhom12.repositories;

import com.nhom12.pojo.DisciplineScore;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Admin
 */
public interface DisciplineScoreRepository {
    List<DisciplineScore> getScores();
    void addOrUpdate(DisciplineScore score);
    DisciplineScore getScoreById(int id);
    List<Object[]> getScoresByStudentId(int studentId, Map<String, String> params);
    List<Object[]> getTotalScoreByStudentId(int studentId, Map<String, String> params);

}
