package xiaozhi.modules.agent.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import xiaozhi.modules.agent.dao.AgentTemplateDao;
import xiaozhi.modules.agent.entity.AgentTemplateEntity;
import xiaozhi.modules.agent.service.AgentTemplateService;

/**
 * @author chenerlei
 * @description 针对表【ai_agent_template(智能体配置模板表)】的数据库操作Service实现
 * @createDate 2025-03-22 11:48:18
 */
@Service
public class AgentTemplateServiceImpl extends ServiceImpl<AgentTemplateDao, AgentTemplateEntity>
        implements AgentTemplateService {

    /**
     * 获取默认模板
     * 
     * @return 默认模板实体
     */
    public AgentTemplateEntity getDefaultTemplate() {
        LambdaQueryWrapper<AgentTemplateEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByAsc(AgentTemplateEntity::getSort)
                .last("LIMIT 1");
        return this.getOne(wrapper);
    }

    /**
     * 更新默认模板中的模型ID
     * 
     * @param modelType 模型类型
     * @param modelId   模型ID
     */
    @Override
    public void updateDefaultTemplateModelId(String modelType, String modelId) {
        modelType = modelType.toUpperCase();
        AgentTemplateEntity defaultTemplate = getDefaultTemplate();
        if (defaultTemplate != null) {
            switch (modelType) {
                case "ASR":
                    defaultTemplate.setAsrModelId(modelId);
                    break;
                case "VAD":
                    defaultTemplate.setVadModelId(modelId);
                    break;
                case "LLM":
                    defaultTemplate.setLlmModelId(modelId);
                    break;
                case "TTS":
                    defaultTemplate.setTtsModelId(modelId);
                    break;
                case "Memory":
                    defaultTemplate.setMemModelId(modelId);
                    break;
                case "Intent":
                    defaultTemplate.setIntentModelId(modelId);
                    break;
            }
            this.updateById(defaultTemplate);
        }
    }
}
