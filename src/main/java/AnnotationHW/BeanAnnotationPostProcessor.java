//package AnnotationHW;
//
//import AnnotationHW.printImp.Printer;
//import org.reflections.Reflections;
//
//import java.lang.reflect.Method;
//import java.util.Set;
//
//public class BeanAnnotationPostProcessor {
//
//	public void findBeans() {
//		// Инициализация Reflections
//		Reflections reflections = new Reflections(Printer.class);
//
//		// Получаем все классы в указанном пакете
//		Set<Class<? extends Printer>> subTypesOf = reflections.getSubTypesOf(Printer.class);
//
//		// Проходим по каждому классу и ищем методы с аннотацией AutoCallable
//		for (Class<?> clazz : subTypesOf) {
//			for (Method method : clazz.getDeclaredMethods()) {
//				if (method.isAnnotationPresent(AutoCallable.class)) {
//					AutoCallable annotation = method.getAnnotation(AutoCallable.class);
//					// Убедимся, что метод не имеет параметров
//					if (method.getParameterCount() == 0) {
//						int counter = annotation.counter();
//						try {
//							for (int i = 0; i < counter; i++) {
//								method.invoke(clazz.getDeclaredConstructor().newInstance());
//							}
//						} catch (Exception e) {
//							e.printStackTrace();
//						}
//					}
//				}
//			}
//		}
//	}
//
//}
//
