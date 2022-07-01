package com.example.springdragoncommon.common.agent.transformer;

import com.example.springdragoncommon.common.agent.enhance.Enhance;
import com.example.springdragoncommon.common.agent.utils.ReflectionUtil;
import org.springframework.asm.ClassVisitor;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author hanbl
 */
public class AgentClassTransformer implements ClassFileTransformer {

    private List<Enhance> enhanceInstances = new ArrayList<Enhance>();
    private List<String> enhances = new ArrayList<String>();

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        return new byte[0];
    }

    public void setEnhances(List<String> enhances) {
        if (enhances != null && enhances.size() > 0) {
            List<String> addList = new ArrayList<String>();
            for (int i = 0, l = enhances.size(); i < l; ++i) {
                if (!this.enhances.contains(enhances.get(i))) {
                    addList.add(enhances.get(i));
                    this.enhanceInstances.add(ReflectionUtil.createInstance(enhances.get(i)));
                }
            }
            this.enhances.addAll(addList);
        }
    }

    public List<String> getEnhances() {
        return Collections.unmodifiableList(this.enhances);
    }
}
