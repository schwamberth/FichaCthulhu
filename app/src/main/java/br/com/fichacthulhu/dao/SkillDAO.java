package br.com.fichacthulhu.dao;

import android.util.Log;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.fichacthulhu.model.Investigador;
import br.com.fichacthulhu.model.Skill;

public class SkillDAO extends BaseDaoImpl<Skill, Long> {

    public static final String TAG = SkillDAO.class.getName();

    public SkillDAO(Class<Skill> dataClass) throws SQLException {
        super(dataClass);
    }

    public SkillDAO(ConnectionSource connectionSource, Class<Skill> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public SkillDAO(ConnectionSource connectionSource, DatabaseTableConfig<Skill> tableConfig) throws SQLException {
        super(connectionSource, tableConfig);
    }

    public Long getSumPontos(Long idInvestigador) {
        QueryBuilder<Skill, Long> queryBuilder = queryBuilder();
        Where<Skill, Long> where = null;
        try {
            where = queryBuilder.where().eq(Skill.Metadata.idInvestigador, idInvestigador);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<Skill> skills = null;
        try {
            skills = where.query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Long soma = 0L;
        if (skills != null) {
            for (Skill skill : skills) {
                soma += skill.getPontos();
            }
        }
        return soma;
    }

    public boolean jaSelecionada(Long idInvestigador, String descricaoSkill) {
        try {
            QueryBuilder<Skill, Long> queryBuilder = queryBuilder();
            Where<Skill, Long> where = queryBuilder.where();
            where.and(
                    where.eq(Skill.Metadata.idInvestigador, idInvestigador),
                    where.eq(Skill.Metadata.descricao, descricaoSkill)
            );
            return queryBuilder.countOf()>0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Skill criar() {
        Skill skill = new Skill();
        try {
            skill = super.createIfNotExists(skill);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return skill;
    }

    public void salvar(Skill skill) {
        try {
            skill.setUltimaAtualizacao(new Date());
            createOrUpdate(skill);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Skill> queryForAll(Long idInvestigador) {
        try {
            return queryBuilder().orderBy(Skill.Metadata.ultimaAtualizacao,false).where().eq(Skill.Metadata.idInvestigador, idInvestigador).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public void remover(Long idInvestigador) {
        List<Skill> skills = queryForAll(idInvestigador);
        try {
            super.delete(skills);
            Log.i("RemoverSkills", skills.size() + " skills removidas.");
        } catch (SQLException e) {
            Log.i("RemoverSkills", "Falha ao remover skills.");
            e.printStackTrace();
        }
    }
}
