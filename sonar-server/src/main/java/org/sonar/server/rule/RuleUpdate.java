/*
 * SonarQube, open source software quality management tool.
 * Copyright (C) 2008-2014 SonarSource
 * mailto:contact AT sonarsource DOT com
 *
 * SonarQube is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * SonarQube is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonar.server.rule;

import org.sonar.api.rule.RuleKey;
import org.sonar.api.server.debt.DebtRemediationFunction;

import javax.annotation.CheckForNull;
import javax.annotation.Nullable;
import java.util.Set;

public class RuleUpdate {

  public static final String DEFAULT_DEBT_CHARACTERISTIC = "_default";

  private final RuleKey ruleKey;

  private boolean changeTags = false, changeMarkdownNote = false, changeDebtSubCharacteristic = false, changeDebtRemediationFunction = false;
  private Set<String> tags;
  private String markdownNote;
  private String debtSubCharacteristicKey;
  private DebtRemediationFunction debtRemediationFunction;

  public RuleUpdate(RuleKey ruleKey) {
    this.ruleKey = ruleKey;
  }

  public RuleKey getRuleKey() {
    return ruleKey;
  }

  @CheckForNull
  public Set<String> getTags() {
    return tags;
  }

  /**
   * Set to <code>null</code> or empty set to remove existing tags.
   */
  public RuleUpdate setTags(@Nullable Set<String> tags) {
    this.tags = tags;
    this.changeTags = true;
    return this;
  }

  @CheckForNull
  public String getMarkdownNote() {
    return markdownNote;
  }

  /**
   * Set to <code>null</code> or empty to remove existing note.
   */
  public RuleUpdate setMarkdownNote(@Nullable String s) {
    this.markdownNote = s;
    this.changeMarkdownNote = true;
    return this;
  }

  @CheckForNull
  public String getDebtSubCharacteristicKey() {
    return debtSubCharacteristicKey;
  }

  /**
   * Set to <code>null</code> to force the characteristic "NONE". Set to value of {@link #DEFAULT_DEBT_CHARACTERISTIC}
   * to reset to default characteristic.
   */
  public RuleUpdate setDebtSubCharacteristic(@Nullable String c) {
    this.debtSubCharacteristicKey = c;
    this.changeDebtSubCharacteristic = true;
    return this;
  }

  @CheckForNull
  public DebtRemediationFunction getDebtRemediationFunction() {
    return debtRemediationFunction;
  }

  public RuleUpdate setDebtRemediationFunction(@Nullable DebtRemediationFunction fn) {
    this.debtRemediationFunction = fn;
    this.changeDebtRemediationFunction = true;
    return this;
  }

  public boolean isChangeTags() {
    return changeTags;
  }

  public boolean isChangeMarkdownNote() {
    return changeMarkdownNote;
  }

  public boolean isChangeDebtSubCharacteristic() {
    return changeDebtSubCharacteristic;
  }

  public boolean isChangeDebtRemediationFunction() {
    return changeDebtRemediationFunction;
  }

  public boolean isEmpty() {
    return !changeMarkdownNote && !changeTags && !changeDebtSubCharacteristic && !changeDebtRemediationFunction;
  }
}
