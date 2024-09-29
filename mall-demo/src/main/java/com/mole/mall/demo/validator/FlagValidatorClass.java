package com.mole.mall.demo.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * 状态标记校验器
 */
public class FlagValidatorClass implements ConstraintValidator<FlagValidator, Integer> {
	private String[] values;

	@Override
	public void initialize(FlagValidator flagValidator) {
		// 初始化允许的值范围
		this.values = flagValidator.value();
	}

	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext constraintValidatorContext) {
		// 验证值是否在允许的范围内
		boolean isValid = false;
		for (String s : values) {
			if (s.equals(String.valueOf(value))) {
				isValid = true;
				break;
			}
		}
		return isValid;
	}
}