/*
 * Copyright (C) 2021 Red Hat, Inc.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *         http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.redhat.appstudio.serviceprovider.service.storage;

import static io.quarkus.vault.test.VaultTestExtension.APP_SECRET_PATH;
import static io.quarkus.vault.test.VaultTestExtension.SECRET_KEY;
import static io.quarkus.vault.test.VaultTestExtension.SECRET_VALUE;
import static org.junit.jupiter.api.Assertions.*;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.vault.VaultKVSecretEngine;
import io.quarkus.vault.test.VaultTestLifecycleManager;
import java.util.Map;
import javax.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.OS;

@DisabledOnOs(OS.WINDOWS) // https://github.com/quarkusio/quarkus/issues/3796
@QuarkusTestResource(VaultTestLifecycleManager.class)
class VaultAccessTokenServiceTest {

  @Inject VaultKVSecretEngine kvSecretEngine;

  //  @RegisterExtension
  //  static final QuarkusUnitTest config =
  //      new QuarkusUnitTest()
  //          .setArchiveProducer(
  //              () ->
  //                  ShrinkWrap.create(JavaArchive.class)
  //                      .addAsResource("application.properties", "application.properties"));

  @Test
  public void secret() {
    Map<String, String> secrets = kvSecretEngine.readSecret(APP_SECRET_PATH);
    assertEquals("{" + SECRET_KEY + "=" + SECRET_VALUE + "}", secrets.toString());
  }
}
