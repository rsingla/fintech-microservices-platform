
package com.target.api;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
  "identifier", "relation", "relation_description", "data_page_link", "imn_identifier", "is_orderable", "is_sellable",
  "general_description", "is_circular_publish", "business_process_status", "dpci", "department_id", "class_id",
  "item_id", "online_description", "store_description", "alternate_description", "features" })
public class Item {

  @JsonProperty("identifier")
  private List<Identifier> identifier = new ArrayList<Identifier>();
  @JsonProperty("relation")
  private String relation;
  @JsonProperty("relation_description")
  private String relationDescription;
  @JsonProperty("data_page_link")
  private String dataPageLink;
  @JsonProperty("imn_identifier")
  private Integer imnIdentifier;
  @JsonProperty("is_orderable")
  private Boolean isOrderable;
  @JsonProperty("is_sellable")
  private Boolean isSellable;
  @JsonProperty("general_description")
  private String generalDescription;
  @JsonProperty("is_circular_publish")
  private Boolean isCircularPublish;
  @JsonProperty("business_process_status")
  private List<BusinessProcessStatu> businessProcessStatus = new ArrayList<BusinessProcessStatu>();
  @JsonProperty("dpci")
  private String dpci;
  @JsonProperty("department_id")
  private Integer departmentId;
  @JsonProperty("class_id")
  private Integer classId;
  @JsonProperty("item_id")
  private Integer itemId;
  @JsonProperty("online_description")
  private OnlineDescription onlineDescription;
  @JsonProperty("store_description")
  private StoreDescription storeDescription;
  @JsonProperty("alternate_description")
  private List<AlternateDescription> alternateDescription = new ArrayList<AlternateDescription>();
  @JsonProperty("features")
  private List<Feature> features = new ArrayList<Feature>();
  @JsonProperty("errors")
  private List<Error> errors = new ArrayList<Error>();

  /**
   * @return The errors
   */
  @JsonProperty("errors")
  public List<Error> getErrors() {
    return errors;
  }

  /**
   * @param errors The errors
   */
  @JsonProperty("errors")
  public void setErrors(List<Error> errors) {
    this.errors = errors;
  }

  /**
   * @return The identifier
   */
  @JsonProperty("identifier")
  public List<Identifier> getIdentifier() {
    return identifier;
  }

  /**
   * @param identifier The identifier
   */
  @JsonProperty("identifier")
  public void setIdentifier(List<Identifier> identifier) {
    this.identifier = identifier;
  }

  /**
   * @return The relation
   */
  @JsonProperty("relation")
  public String getRelation() {
    return relation;
  }

  /**
   * @param relation The relation
   */
  @JsonProperty("relation")
  public void setRelation(String relation) {
    this.relation = relation;
  }

  /**
   * @return The relationDescription
   */
  @JsonProperty("relation_description")
  public String getRelationDescription() {
    return relationDescription;
  }

  /**
   * @param relationDescription The relation_description
   */
  @JsonProperty("relation_description")
  public void setRelationDescription(String relationDescription) {
    this.relationDescription = relationDescription;
  }

  /**
   * @return The dataPageLink
   */
  @JsonProperty("data_page_link")
  public String getDataPageLink() {
    return dataPageLink;
  }

  /**
   * @param dataPageLink The data_page_link
   */
  @JsonProperty("data_page_link")
  public void setDataPageLink(String dataPageLink) {
    this.dataPageLink = dataPageLink;
  }

  /**
   * @return The imnIdentifier
   */
  @JsonProperty("imn_identifier")
  public Integer getImnIdentifier() {
    return imnIdentifier;
  }

  /**
   * @param imnIdentifier The imn_identifier
   */
  @JsonProperty("imn_identifier")
  public void setImnIdentifier(Integer imnIdentifier) {
    this.imnIdentifier = imnIdentifier;
  }

  /**
   * @return The isOrderable
   */
  @JsonProperty("is_orderable")
  public Boolean getIsOrderable() {
    return isOrderable;
  }

  /**
   * @param isOrderable The is_orderable
   */
  @JsonProperty("is_orderable")
  public void setIsOrderable(Boolean isOrderable) {
    this.isOrderable = isOrderable;
  }

  /**
   * @return The isSellable
   */
  @JsonProperty("is_sellable")
  public Boolean getIsSellable() {
    return isSellable;
  }

  /**
   * @param isSellable The is_sellable
   */
  @JsonProperty("is_sellable")
  public void setIsSellable(Boolean isSellable) {
    this.isSellable = isSellable;
  }

  /**
   * @return The generalDescription
   */
  @JsonProperty("general_description")
  public String getGeneralDescription() {
    return generalDescription;
  }

  /**
   * @param generalDescription The general_description
   */
  @JsonProperty("general_description")
  public void setGeneralDescription(String generalDescription) {
    this.generalDescription = generalDescription;
  }

  /**
   * @return The isCircularPublish
   */
  @JsonProperty("is_circular_publish")
  public Boolean getIsCircularPublish() {
    return isCircularPublish;
  }

  /**
   * @param isCircularPublish The is_circular_publish
   */
  @JsonProperty("is_circular_publish")
  public void setIsCircularPublish(Boolean isCircularPublish) {
    this.isCircularPublish = isCircularPublish;
  }

  /**
   * @return The businessProcessStatus
   */
  @JsonProperty("business_process_status")
  public List<BusinessProcessStatu> getBusinessProcessStatus() {
    return businessProcessStatus;
  }

  /**
   * @param businessProcessStatus The business_process_status
   */
  @JsonProperty("business_process_status")
  public void setBusinessProcessStatus(List<BusinessProcessStatu> businessProcessStatus) {
    this.businessProcessStatus = businessProcessStatus;
  }

  /**
   * @return The dpci
   */
  @JsonProperty("dpci")
  public String getDpci() {
    return dpci;
  }

  /**
   * @param dpci The dpci
   */
  @JsonProperty("dpci")
  public void setDpci(String dpci) {
    this.dpci = dpci;
  }

  /**
   * @return The departmentId
   */
  @JsonProperty("department_id")
  public Integer getDepartmentId() {
    return departmentId;
  }

  /**
   * @param departmentId The department_id
   */
  @JsonProperty("department_id")
  public void setDepartmentId(Integer departmentId) {
    this.departmentId = departmentId;
  }

  /**
   * @return The classId
   */
  @JsonProperty("class_id")
  public Integer getClassId() {
    return classId;
  }

  /**
   * @param classId The class_id
   */
  @JsonProperty("class_id")
  public void setClassId(Integer classId) {
    this.classId = classId;
  }

  /**
   * @return The itemId
   */
  @JsonProperty("item_id")
  public Integer getItemId() {
    return itemId;
  }

  /**
   * @param itemId The item_id
   */
  @JsonProperty("item_id")
  public void setItemId(Integer itemId) {
    this.itemId = itemId;
  }

  /**
   * @return The onlineDescription
   */
  @JsonProperty("online_description")
  public OnlineDescription getOnlineDescription() {
    return onlineDescription;
  }

  /**
   * @param onlineDescription The online_description
   */
  @JsonProperty("online_description")
  public void setOnlineDescription(OnlineDescription onlineDescription) {
    this.onlineDescription = onlineDescription;
  }

  /**
   * @return The storeDescription
   */
  @JsonProperty("store_description")
  public StoreDescription getStoreDescription() {
    return storeDescription;
  }

  /**
   * @param storeDescription The store_description
   */
  @JsonProperty("store_description")
  public void setStoreDescription(StoreDescription storeDescription) {
    this.storeDescription = storeDescription;
  }

  /**
   * @return The alternateDescription
   */
  @JsonProperty("alternate_description")
  public List<AlternateDescription> getAlternateDescription() {
    return alternateDescription;
  }

  /**
   * @param alternateDescription The alternate_description
   */
  @JsonProperty("alternate_description")
  public void setAlternateDescription(List<AlternateDescription> alternateDescription) {
    this.alternateDescription = alternateDescription;
  }

  /**
   * @return The features
   */
  @JsonProperty("features")
  public List<Feature> getFeatures() {
    return features;
  }

  /**
   * @param features The features
   */
  @JsonProperty("features")
  public void setFeatures(List<Feature> features) {
    this.features = features;
  }

}
