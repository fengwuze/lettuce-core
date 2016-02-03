package com.lambdaworks.apigenerator;

import java.io.File;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;

import com.github.javaparser.ast.comments.Comment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.ReferenceType;
import com.github.javaparser.ast.type.Type;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;

/**
 * Create reactive API based on the templates.
 * 
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 */
@RunWith(Parameterized.class)
public class CreateReactiveApi {

    private Set<String> KEEP_METHOD_RESULT_TYPE = ImmutableSet.of("digest", "close", "isOpen", "BaseRedisCommands.reset",
            "getStatefulConnection");

    private CompilationUnitFactory factory;

    @Parameterized.Parameters(name = "Create {0}")
    public static List<Object[]> arguments() {
        List<Object[]> result = Lists.newArrayList();

        for (String templateName : Constants.TEMPLATE_NAMES) {
            result.add(new Object[] { templateName });
        }

        return result;
    }

    /**
     *
     * @param templateName
     */
    public CreateReactiveApi(String templateName) {

        String targetName = templateName.replace("Commands", "ReactiveCommands");
        File templateFile = new File(Constants.TEMPLATES, "com/lambdaworks/redis/api/" + templateName + ".java");
        String targetPackage;

        if (templateName.contains("RedisSentinel")) {
            targetPackage = "com.lambdaworks.redis.sentinel.api.rx";
        } else {
            targetPackage = "com.lambdaworks.redis.api.rx";
        }

        factory = new CompilationUnitFactory(templateFile, Constants.SOURCES, targetPackage, targetName, commentMutator(),
                methodTypeMutator(), methodDeclaration -> true, importSupplier(), null, methodCommentMutator());
    }

    /**
     * Mutate type comment.
     * 
     * @return
     */
    protected Function<String, String> commentMutator() {
        return s -> s.replaceAll("\\$\\{intent\\}", "Observable commands").replaceAll("@since 3.0", "@since 4.0")
                + "* @generated by " + getClass().getName() + "\r\n ";
    }

    protected Function<Comment, Comment> methodCommentMutator() {
        return comment -> {
            if(comment != null && comment.getContent() != null){
                comment.setContent(comment.getContent().replaceAll("List&lt;(.*)&gt;", "$1").replaceAll("Set&lt;(.*)&gt;", "$1"));
            }
            return comment;
        };
    }

    /**
     * Mutate type to async result.
     * 
     * @return
     */
    protected Function<MethodDeclaration, Type> methodTypeMutator() {
        return method -> {

            ClassOrInterfaceDeclaration classOfMethod = (ClassOrInterfaceDeclaration) method.getParentNode();
            if (KEEP_METHOD_RESULT_TYPE.contains(method.getName())
                    || KEEP_METHOD_RESULT_TYPE.contains(classOfMethod.getName() + "." + method.getName())) {
                return method.getType();
            }

            String typeAsString = method.getType().toStringWithoutComments().trim();
            if (typeAsString.equals("void")) {
                typeAsString = "Success";
            }

            if (typeAsString.startsWith("List<")) {
                typeAsString = typeAsString.substring(5, typeAsString.length() - 1);
            } else if (typeAsString.startsWith("Set<")) {
                typeAsString = typeAsString.substring(4, typeAsString.length() - 1);
            }

            return new ReferenceType(new ClassOrInterfaceType("Observable<" + typeAsString + ">"));
        };
    }

    /**
     * Supply addititional imports.
     * 
     * @return
     */
    protected Supplier<List<String>> importSupplier() {
        return () -> ImmutableList.of("rx.Observable");
    }

    @Test
    public void createInterface() throws Exception {
        factory.createInterface();
    }
}
