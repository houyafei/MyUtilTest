package annotation;


import com.sun.mirror.apt.*;
import com.sun.mirror.declaration.MethodDeclaration;
import com.sun.mirror.declaration.Modifier;
import com.sun.mirror.declaration.ParameterDeclaration;
import com.sun.mirror.declaration.TypeDeclaration;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 接口提取处理器
 *
 * @author yafei.hou  on 2018/8/20
 */
public class InterfaceExtractorProcessor implements AnnotationProcessor {

    private final AnnotationProcessorEnvironment environment;

    private ArrayList<MethodDeclaration> interfaceMethod = new ArrayList<>();

    public InterfaceExtractorProcessor(AnnotationProcessorEnvironment environment) {
        this.environment = environment;

    }

    @Override
    public void process() {

        for (TypeDeclaration typeDec : environment.getSpecifiedTypeDeclarations()) {
            ExtractInterface extractInterface = typeDec.getAnnotation(ExtractInterface.class);
            if (extractInterface == null) {
                continue;
            }
            for (MethodDeclaration methodDeclaration : typeDec.getMethods()) {
                if (methodDeclaration.getModifiers().contains(Modifier.PUBLIC) &&
                        !methodDeclaration.getModifiers().contains(Modifier.STATIC)) {
                    interfaceMethod.add(methodDeclaration);
                }
            }
            if (interfaceMethod.size() > 0) {
                try {
                    PrintWriter printWriter = environment.getFiler().createSourceFile(extractInterface.value());
                    printWriter.println("package " + typeDec.getPackage().getQualifiedName() + ";");
                    printWriter.println("public interface " + extractInterface.value() + "{");
                    for (MethodDeclaration method : interfaceMethod) {
                        printWriter.print("public ");
                        printWriter.print(method.getReturnType() + " ");
                        printWriter.print(method.getSimpleName() + " (");
                        AtomicInteger i = new AtomicInteger();
                        for (ParameterDeclaration param : method.getParameters()) {
                            printWriter.print(param.getType() + " " + param.getSimpleName());
                            if (i.incrementAndGet() < method.getParameters().size()) {
                                printWriter.print(" ,");
                            }
                        }
                        printWriter.print(");");
                    }
                    printWriter.println("}");
                    System.out.println("____________________");
                    printWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
