package com.asily.utils;


public class crawler {
    public void runPythonScript() {
//        // Python脚本路径
//        String pythonScriptPath = "C:/project/crawler/cf.py"; // 替换为实际路径
//
//        // 题目属性参数
//        String problemSet = "1353";
//        String problemId = "B";
//
//        // 设置Python解释器路径（可选，取决于系统设置）
//        String pythonInterpreter = "python"; // Linux/Mac中通常为"python3"，在Windows中可能为"python"
//
//        // 使用ProcessBuilder构建Python命令
//        ProcessBuilder pb = new ProcessBuilder(
//                pythonInterpreter, pythonScriptPath, problemSet, problemId
//        );
//        try{
//            // 启动进程
//            Process process = pb.start();
//
//            // 捕获Python脚本输出
//            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
//            String line;
//            while ((line = reader.readLine()) != null) {
//                System.out.println(line);
//            }
//
//            // 捕获Python脚本的错误输出（如果有）
//            BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
//            while ((line = errorReader.readLine()) != null) {
//                System.err.println(line);
//            }
//
//            // 等待脚本执行完成
//            int exitCode = process.waitFor();
//            System.out.println("Python script exited with code: " + exitCode);
//
//        } catch(Exception e){
//            e.printStackTrace();
//        }


//        System.setProperty("python.import.site", "false");
//        PyFunction pyFunction;
//        PythonInterpreter interpreter = new PythonInterpreter();
//        interpreter.execfile("C:/project/crawler/cf.py");
//        // 第一个参数为期望获得的函数（变量）的名字，第二个参数为期望返回的对象类型
//        pyFunction = interpreter.get("cf", PyFunction.class);
//
//        int a = 1353;
//        String b ="B";
//        //调用函数，如果函数需要参数，在Java中必须先将参数转化为对应的“Python类型”
//        PyObject pyobj = pyFunction.__call__(new PyInteger(a), new PyString(b));
//        System.out.println(pyobj);

    }


}

