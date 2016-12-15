package cn.thinkjoy.gaokao360.dto;

import cn.thinkjoy.common.domain.CreateBaseDomain;
import cn.thinkjoy.gaokao360.domain.Major;

/**
 * Created by admin on 2015/12/29.
 */
public class MajorDTO extends CreateBaseDomain<Long> {
    String majorCode;
    String majorType;
    String majorTypeName;
    String disciplineCategories;
    String disciplineCategoriesName;
    String majorCategory;
    String majorCategoryName;
    String majorName;
    String degreeOffered;
    String majorIntroduce;
    String specialisation;
    String schoolingDuration;
    String offerCourses;
    String employmentRate;

    public String getMajorCode() {
        return majorCode;
    }

    public void setMajorCode(String majorCode) {
        this.majorCode = majorCode;
    }

    public String getMajorType() {
        return majorType;
    }

    public void setMajorType(String majorType) {
        this.majorType = majorType;
    }

    public String getMajorTypeName() {
        return majorTypeName;
    }

    public void setMajorTypeName(String majorTypeName) {
        this.majorTypeName = majorTypeName;
    }

    public String getDisciplineCategories() {
        return disciplineCategories;
    }

    public void setDisciplineCategories(String disciplineCategories) {
        this.disciplineCategories = disciplineCategories;
    }

    public String getDisciplineCategoriesName() {
        return disciplineCategoriesName;
    }

    public void setDisciplineCategoriesName(String disciplineCategoriesName) {
        this.disciplineCategoriesName = disciplineCategoriesName;
    }

    public String getMajorCategory() {
        return majorCategory;
    }

    public void setMajorCategory(String majorCategory) {
        this.majorCategory = majorCategory;
    }

    public String getMajorCategoryName() {
        return majorCategoryName;
    }

    public void setMajorCategoryName(String majorCategoryName) {
        this.majorCategoryName = majorCategoryName;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public String getDegreeOffered() {
        return degreeOffered;
    }

    public void setDegreeOffered(String degreeOffered) {
        this.degreeOffered = degreeOffered;
    }

    public String getMajorIntroduce() {
        return majorIntroduce;
    }

    public void setMajorIntroduce(String majorIntroduce) {
        this.majorIntroduce = majorIntroduce;
    }

    public String getSpecialisation() {
        return specialisation;
    }

    public void setSpecialisation(String specialisation) {
        this.specialisation = specialisation;
    }

    public String getSchoolingDuration() {
        return schoolingDuration;
    }

    public void setSchoolingDuration(String schoolingDuration) {
        this.schoolingDuration = schoolingDuration;
    }

    public String getOfferCourses() {
        return offerCourses;
    }

    public void setOfferCourses(String offerCourses) {
        this.offerCourses = offerCourses;
    }

    public String getEmploymentRate() {
        return employmentRate;
    }

    public void setEmploymentRate(String employmentRate) {
        this.employmentRate = employmentRate;
    }
}
